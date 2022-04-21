package com.test.socket.modules.dashin;

import com.test.socket.external.dashin.cputil.ICpCybos;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DashinConnector {

    public static void connect(ICpCybos iCpCybos) throws Exception {
        // creon plus에 연결 여부 체크
        if(iCpCybos.isConnect() == 1)
            return;

        // creon 모듈을 모두 종료
        stopCreonProcessWaitFor5m();

        // process 구동 및 로그인 진행
        Process p = Runtime.getRuntime().exec("C:\\CREON\\STARTER\\coStarter.exe /prj:cp /id:khk7129 /pwd:woals1! /pwdcert:khk7129!@# /autostart");
        Thread.sleep(30000);

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
