package com.test.socket.app;

import com.test.socket.dto.Greeting;
import com.test.socket.dto.HelloMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.time.LocalDateTime;

@Slf4j
@RestController
public class PriceController {

    @MessageMapping(value="/test")
    public String handle(String test){
        return "[" + LocalDateTime.now().toString() + "] message: " + test;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception{
        log.info("PriceController.greeting(), msg: "+ message.getName());
        Thread.sleep(1000); // simulated delay

        log.info("PriceController.greeting(), return msg: hello, "+message.getName()+"!");
        return new Greeting("hello, "+ HtmlUtils.htmlEscape(message.getName()) +"!");
    }
}
