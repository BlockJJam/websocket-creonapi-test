# SockJS를 활용한 Spring Websocket 서버와 클라이언트

---

### 활용스펙

- Spring Framework
    - Websocket, SockJS, STOMP, JQuery
- Gradle
- JDK 11
- Intellij

### 프로젝트 설명

- Spring Framework의 WebSocket과 SockJS, STOMP를 이용해서 웹소켓 서버와 JQuery를 이용한 웹소켓 클라이언트를 테스트 해본다

### SockJS를 테스트 하려는 이유

- 애플리케이션이 WebSocket API 사용을 허락하지만, 브라우저에서 WebSocket을 지원하지 않아도 코드 변경없이 런타임에 필요할 때 대체가 가능
- SockJS의 구성
    - SockJS Protocol
    - SockJS Javascript Client - 브라우저에서 사용되는 클라이언트 라이브러리
    - SockJS Server 구현  - spring-websocket 모듈로 제공
    - SockJS Java Client - (spring ver 4.1 이후부터) spring-websocket 모듈로 제공
- SockJS의 전송타입 3가지
    - WebSocket
    - HTTP Streaming
    - HTTP Long Polling

### WebSocket Emulation Process

- SockJSClient
    - 서버의 정보를 얻기 위해, “GET/Info”를 호출하는데 이를 통해 얻는 정보
        - 서버가 WebSocket을 지원하는가
        - Cookies 지원이 필요한지 여부
        - CORS를 위한 Origin 정보
    - 위 정보를 토대로 SockJS는 어떤 정보를 리턴할 지 결정한다

- 모든 ws전송 요청은 다음의 URL 구조를 가진다

  `https://host:port/myApp/myEndPoint/{server-id}/{session-id}/{trasport}`

    - 예시

  ![notion_temp](https://user-images.githubusercontent.com/57485510/163324094-a810a99e-9f43-414a-9909-6321522b8abc.png)


    - `server-id`: 클러스터에서 요청을 라우팅하는데 사용하나 이외에는 의미 없음
    - `session-id`: SockJS session에 소속하는 HTTP 요청과 연관성있음
    - `transport`: 전송 타입( websocket, xhr-streaming, xhr-polling)

### SockJS의 메시지 Frame 최소화를 위한 노력

- SockJS는 메세지 Frame의 크기를 최소화하기 위해 노력한다
    - "o" - (open frame)을 초기에 전송하고, 메세지는 ["msg1", "msg2"]와 같은 JSON-Encoded 배열로서 전달되며,
    - "h" - (heartbeat frame)는 기본적으로 25초간 메세지 흐름이 없는 경우에 전송하고
    - "c" - (close frame)는 해당 세션을 종료한다.


### Websocket Server 프로젝트 코드 확인( 실제 코드 일부)

- config > `WebSocketConfig`

```java
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /* Stomp websocket config */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/price").withSockJS(); // 1) 접속 커넥션을 요청할 endpoint 지정
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/app"); // 2) app prefix가 붙어서 들어오는 요청을 라우팅해준다, params type: String[]
        config.enableSimpleBroker("/topic", "/queue") // 3) 들어온 요청을 어떤 브로커로 보낼건지 목적지를 설정(Client도 해당된다고 본다)
                .setTaskScheduler(taskScheduler())
                .setHeartbeatValue(new long[]{3000L, 3000L});
    }

    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.initialize();;
        return taskScheduler;
    }
}
```

- app > `PriceController`
- request url: `/app/hello`
- destination url: `/topic/greetings`

```java
@MessageMapping("/hello")
@SendTo("/topic/greetings")
public Greeting greeting(HelloMessage message) throws Exception{
    log.info("PriceController.greeting(), msg: "+ message.getName());
    Thread.sleep(1000); // simulated delay

    log.info("PriceController.greeting(), return msg: hello, "+message.getName()+"!");
    return new Greeting("hello, "+ HtmlUtils.htmlEscape(message.getName()) +"!");
}
```

- dto > `Greeting`, `HelloMessage`
- 요청과 응답에 활용할 DTO

```java
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Greeting {
    private String content;
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HelloMessage {
    private String name;
}
```

- security > `SecurityConfig`
- 해당 페이지를 frame 또는 iframe, object에서 렌더링할 수 있는지 여부를 나타내는데 사용되고, 사이트 내 컨텐츠들이 다른 사이트에 포함되지 않도록해 'clickjacking' 공격을 막아내기 위해 사용된

```java
@Configuration
@Slf4j
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin();
    }
}
```

- static > `app.js`, `index.html`
    - 해당 내용은 코드에서 확인

# 소켓 서버 테스트 결과

---

### 간단한 소켓 통신 예제

- 서버에 connect/disconnect 가능
- 서버에 이름 문자열을 요청하면, 인사 메시지 전송
  ![notion_temp2](https://user-images.githubusercontent.com/57485510/163324579-a1b467cc-8fa7-4ff5-9130-8dbcb66fea19.png)
