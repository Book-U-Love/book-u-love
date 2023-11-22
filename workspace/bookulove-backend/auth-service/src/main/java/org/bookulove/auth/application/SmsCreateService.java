package org.bookulove.auth.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.auth.application.port.in.SmsCreateUseCase;
import org.bookulove.auth.application.port.in.dto.command.SmsCreateCmd;
import org.bookulove.auth.application.port.out.SmsCreatePort;
import org.bookulove.auth.application.port.out.SmsSendPort;
import org.bookulove.common.annotation.UseCase;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class SmsCreateService implements SmsCreateUseCase {

    private final SmsSendPort smsSendPort;
    private final SmsCreatePort smsCreatePort;

    @Override
    public void createSms(SmsCreateCmd cmd) {
        log.info("핸드폰 번호 cmd: {}", cmd.toString());

        String authCode = createAuthCode();
        log.info("인증번호: {}", authCode);

        smsCreatePort.createSms(cmd.phoneNumber(), authCode);
        smsSendPort.sendSms(cmd.phoneNumber(), authCode);
    }

    private String createAuthCode() {
        Random rand = new Random();
        String numStr = "";

        for(int i=0; i<6; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr += ran;
        }

        return numStr;
    }
}
