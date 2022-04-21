package com.test.socket.app;

import com.test.socket.dto.TickData;
import com.test.socket.external.dashin.cpdib.IDib;
import com.test.socket.external.dashin.cpdib.events._IDibEvents;
import com4j.EventCookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PriceService {

    // 메모리에 실시세에 사용할 종목리스트를 저장
    static String[] symbolList = {"A005930","A001450","A000660"};

    // 저장된 종목리스트로 실시세를 받아오는 메서드 작성
    public static void openSubscribeEventForReservingRealtimePrice(IDib iStockCur, SimpMessagingTemplate template) throws Exception {
        for(String symbolCode : symbolList){
            EventCookie resultCookie = receiveTickData(iStockCur, template, symbolCode);
            if(resultCookie != null){
                log.info("증권사 실시세 API를 통해 {} Subscribe를 시작했습니다 ",symbolCode);
            }
        }
    }

    public static EventCookie receiveTickData(IDib iStockCur, SimpMessagingTemplate template, String symbolCode) throws Exception{        // creon에 로그인 및 커넥션이 제대로 되어있는지 확인
        iStockCur.setInputValue(0, symbolCode);

        EventCookie cookie = iStockCur.advise(_IDibEvents.class, new _IDibEvents() {
            @Override
            public void received() {
                TickData tickData = new TickData(
                        (String) iStockCur.getHeaderValue(0),
                         iStockCur.getHeaderValue(18),
                         iStockCur.getHeaderValue(13));

                log.info("시세 데이터: {}", tickData.toString());

                template.convertAndSend("/realtime/"+ tickData.getCode(), tickData);
            }
        });

        iStockCur.subscribe();
        return cookie;
//        cookie.close();
    }
}
