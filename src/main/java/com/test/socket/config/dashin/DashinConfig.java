package com.test.socket.config.dashin;

import com.test.socket.app.PriceService;
import com.test.socket.external.dashin.cpdib.IDib;
import com.test.socket.external.dashin.cputil.ClassFactory;
import com.test.socket.external.dashin.cputil.ICpCodeMgr;
import com.test.socket.external.dashin.cputil.ICpCybos;
import com.test.socket.external.dashin.cputil.ICpStockCode;
import com.test.socket.modules.dashin.DashinConnector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class DashinConfig {
    private final SimpMessagingTemplate template;
    @Bean
    public ICpStockCode iCpStockCode(){
        return ClassFactory.createCpStockCode();
    }

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
        return cpCybos;
    }

    @Bean
    public ICpCodeMgr iCpCodeMgr(){ return ClassFactory.createCpCodeMgr();}

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

        return stockCur;
    }

    @Bean
    public IDib iStockMst(){ return com.test.socket.external.dashin.cpdib.ClassFactory.createStockMst();}

}
