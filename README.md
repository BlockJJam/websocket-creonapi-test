# 실시세 테스트 Spring Websocket 서버

### System Main Spec

---

- **Spring Boot:2.5.12**
- **Spring Websocket - STOMP**
- **Com4j:2.1 - tlbimp**
- **Creon Plus API(대신증권)**
- **Intellij**

> 참고: 
> 
> Websocket 개념에 대한 소개는 [Web Socket 이란?](https://www.notion.so/Web-Socket-e70949a212934b208dcb5a262d6dc35d)를 클릭
> 
> Spring의 Websocket 사용법은 [Spring 기반 Websocket Server활용](https://www.notion.so/Spring-WebSocket-Server-83b932f1b3c44ec794819986251560eb)를 클릭 
> 
> Com4j로 CreonAPI를 테스트하는 방법은 [대신증권 Creon api dll을 com4j로 테스트](https://www.notion.so/Creon-api-dll-com4j-2830c1395f924afd99d885ccceddfa54)를 클릭


### Project Introduce

---

- Spring 기반의 웹소켓 서버를 통해 Creon API에서 제공하는 실시세 데이터를 클라이언트에게 전달하는 프로젝트
- Application이 구동할 때 Creon API에 Creon Connection과 실시세 Subscibe 이벤트가 붙어야 한다
- 실시세 Subscribe하는 종목은 3개로 지정
- 테스트 페이지를 통해 클라이언트가 웹소켓에 연결하면 한가지 종목에 대해 Subscribe가능하도록 지정

### Implementation

---

- Websocket Stomp서버를 활용해 자동화된 세션관리와 간편한 설정으로 routing을 설정합니다

    ```java
    @Configuration
    @EnableWebSocketMessageBroker
    public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
        /* Stomp websocket config */
        @Override
        public void registerStompEndpoints(StompEndpointRegistry registry) {
    				// 1)
            registry.addEndpoint("/price").setAllowedOrigins("*");
        }
    
        @Override
        public void configureMessageBroker(MessageBrokerRegistry config) {
    				// 2)
            config.setApplicationDestinationPrefixes("/app"); // app prefix가 붙어서 들어오는 요청을 라우팅해준다, params type: String[]
    
            // 3)
    				config.enableSimpleBroker("/topic", "/realtime") // 들어온 요청을 어떤 브로커로 보낼건지 목적지를 설정(Client도 해당된다고 본다)
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

    - `registerStompEndpoints(...)`: “ws:API_URL/price”로 붙을 수 있도록 설정해줍니다
    - `config.setApplicationDestinationPrefixes(”/app”)`: app prefix가 붙어 들어오는 요청들을 라우팅해줍니다
    - `config.enableSimpleBroker("/topic", "/realtime")`: 들어온 요청을 어떤 브로커로 보낼지 라우팅해줍니다.

- Spring Application이 구동할 때, 함께 실행되어야 하는 로직
    - Creon API는 Spring Application이 구동할 때, 함께 연결되어야 합니다
    - Creon API의 실시간 시세를 Subscribe하는 로직도 Application이 구동할 때, 함께 실행되어야 합니다

    ```java
    @Slf4j
    @RequiredArgsConstructor
    @Configuration
    public class DashinConfig {
        private final SimpMessagingTemplate template;
    
        /**
         * CpCybos 객체를 Bean에 등록할 때 Creon Plus에 실행프로그램과 로그인을 통해 연결해주는 로직을 실행해준다
         * 다만, 이를 제대로 활용하기 위해서는 다음과 같은 경우를 테스트해봐야한다
         *  1) Connection이 끊어졌을 때, 어떻게 자동화되서 Creon을 돌려줄 것인지
         *  2) 요청이 들어왔을 때 마다, Connection과 밑에 StockCur의 실시세 이벤트가 잘 살아있는지 어떻게 체크할 것인지
         * @return
         * @throws Exception
         */
        @Bean
        public ICpCybos iCpCybos() throws Exception {
    
            ICpCybos cpCybos = ClassFactory.createCpCybos();
            DashinConnector.connect(cpCybos);
            return cpCybos; // 1)
        }
    
        /**
         * 실시세를 담당하는 StockCur객체를 Bean으로 등록할 때, 증권사 API에 실시세 Subscribe를 열어준다
         * 다만, 현재 이슈로 생각되어지는 점: 
         *  1) Creon과 세션이 끊겼을 때나 unSubscribe 하는 부분(증권사에 대한 것만)에서 어떻게 등록된 증권사 이벤트를 어떻게 close해주면서 대처를 해줄 것인지
         *  2) 스케쥴링을 통해 이벤트 등록되서 subscribe하는 것을 어떻게 unscribe와 event close를 해줄 것인지
         * @return
         * @throws Exception
         */
        @Bean()
        public IDib iStockCur() throws Exception {
            log.info("DashinConfig.iStockCur()");
            IDib stockCur = com.test.socket.external.dashin.cpdib.ClassFactory.createStockCur();
            PriceService.openSubscribeEventForReservingRealtimePrice(stockCur, template);
    
            return stockCur; // 2)
        }
    }
    ```

  > 위 주석은, 실제 서비스로 만들기 위해서 필요한 이슈들 정리
  >
    - `cpCybos Bean`: ICpCybos 객체를 생성하여 CreonAPI에 연결한 후에 빈으로 등록
    - `stockCur Bean`: IStockCur 객체를 생성하여 CreonAPI에 실시세를 Subscribe하는 이벤트를 PriceService에 요청한 후에 bean으로 등록
- CreonAPI Connector를 통해 Creon API에 connection을 어떻게 하는지 확인합니다

    ```java
    @Slf4j
    public class DashinConnector {
    
        public static void connect(ICpCybos iCpCybos) throws Exception {
            // creon plus에 연결 여부 체크
            if(iCpCybos.isConnect() == 1)
                return;
    
            // creon 모듈 프로세스를 종료
            stopCreonProcessWaitFor5m();
    
            // process 구동 및 로그인 진행
            Process p = Runtime.getRuntime().exec("C:\\CREON\\STARTER\\coStarter.exe /prj:cp /id:khk7129 /pwd:woals1! /pwdcert:khk7129!@# /autostart");
            Thread.sleep(5000);
    
            log.info("Creon Plus 연결에 {}했습니다",iCpCybos.isConnect() == 1? "성공":"실패");
        }
    
        private static void stopCreonProcessWaitFor5m() throws Exception {
            Runtime.getRuntime().exec("cmd /c taskkill /IM CpStart.exe /F");
            Runtime.getRuntime().exec("cmd /c taskkill /IM coStarter.exe /F");
            Runtime.getRuntime().exec("cmd /c taskkill /IM DibServer.exe /F");
            Runtime.getRuntime().exec("cmd /c wmic process where \"name like \\'%coStarter%\\'\" call terminate");
            Runtime.getRuntime().exec("cmd /c wmic process where \"name like \\'%CpStart%\\'\" call terminate");
            Runtime.getRuntime().exec("cmd /c wmic process where \"name like \\'%Dib%\\'\" call terminate");
            Thread.sleep(5000);
        }
    }
    ```

    - `stopCreonProcessWaitFor5m()`: CreonAPI 모듈이 실행할 때 사용되는 프로세스들을 모두 종료
    - `Thread.sleep(...)`을 사용한 이유: CreonAPI는 커넥션에 대한 동기적 처리 방식을 제공하지 않습니다.

  > 코드에 대한 상세한 설명은 코드로 직접 확인하셔도 무난할 것으로 보입니다
- CreonAPI `PriceService`를 통해 Creon API에 실시세를 Subscibe를 어떻게 하는지 확인합니다

    ```java
    @Slf4j
    @RequiredArgsConstructor
    @Service
    public class PriceService {
    
    		// 1)
        // 메모리에 실시세에 사용할 종목리스트를 저장
        static String[] symbolList = {"A005930","A001450","A000660"};
    
    		// 2)
        // 저장된 종목리스트로 실시세를 받아오는 메서드 작성
        public static void openSubscribeEventForReservingRealtimePrice(IDib iStockCur, SimpMessagingTemplate template) throws Exception {
            for(String symbolCode : symbolList){
                EventCookie resultCookie = receiveTickData(iStockCur, template, symbolCode);
                if(resultCookie != null){
                    log.info("증권사 실시세 API를 통해 {} Subscribe를 시작했습니다 ",symbolCode);
                }
            }
        }
    
    		// 3)
        public static EventCookie receiveTickData(IDib iStockCur, SimpMessagingTemplate template, String symbolCode) throws Exception{        // creon에 로그인 및 커넥션이 제대로 되어있는지 확인
            iStockCur.setInputValue(0, symbolCode);
    
    				// 4)
            EventCookie cookie = iStockCur.advise(_IDibEvents.class, new _IDibEvents() {
                @Override
                public void received() {
                    TickData tickData = new TickData(
                            (String) iStockCur.getHeaderValue(0),
                             iStockCur.getHeaderValue(18),
                             iStockCur.getHeaderValue(13));
    
                    log.info("삼성전자 데이터: {}", tickData.toString());
    								// 5)
                    template.convertAndSend("/realtime/A005930", tickData);
                }
            });
    
            iStockCur.subscribe();
            return cookie;
    //        cookie.close();
        }
    }
    ```

    - `symbolList` : 3가지 종목에 대한 테스트를 진행합니다
    - `openSubscribeEventForReservingRealtimePrice(...)`: 종목리스트 별로 실시세 데이터 이벤트 등록을 요청합니다
    - `receiveTickData(...)`: Com4j를 이용해 시스템에 Subscibe 기능에 대한 이벤트를 등록하고, Creon API에 실시세를 Subscribe 요청하는 메서드입니다
    - `iStockCur.advise(...)`: 시스템에 이벤트를 등록하는 기능으로, Creon API에 실시세 이벤트를 담당하는 _IDibEvents클래스를 활용했습니다
    - `template.convertAndSend("/realtime/"+ tickData.getCode(), tickData)`
      `"/realtime/"+ tickData.getCode()`: 해당 이름으로 subscribe중인 브로커(Websocket 클라이언트로 테스트)를 목적지로 설정
      `tickData`: TikData객체로 CreonAPI로 넘어온 데이터를 담을 DTO
      `convertAndSend(...)`:  위 파라미터를 통해 브로커에게 메시지를 전달

> 실제 SubScribe를 하는 코드는 `resources > static > app.js`에서 로직을 확인하실 수 있습니다. Websocket 서버에 대한 설명만 포함합니다
>

- 실제 실행 테스트한 화면

  ![realtime1](https://user-images.githubusercontent.com/57485510/164345858-e106e92b-a46d-435d-b898-a6bbb96e98dd.png)
