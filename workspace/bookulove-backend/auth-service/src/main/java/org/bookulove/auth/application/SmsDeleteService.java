package org.bookulove.auth.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.auth.application.port.in.SmsDeleteUseCase;
import org.bookulove.auth.application.port.in.dto.command.SmsDeleteCmd;
import org.bookulove.auth.application.port.out.SmsDeletePort;
import org.bookulove.auth.application.port.out.SmsFindPort;
import org.bookulove.auth.global.exception.AuthServiceException;
import org.bookulove.common.annotation.UseCase;
import org.bookulove.common.error.ErrorCode;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class SmsDeleteService implements SmsDeleteUseCase {

    private final SmsFindPort smsFindPort;
    private final SmsDeletePort smsDeletePort;

    @Override
    public void deleteSms(SmsDeleteCmd cmd) {
        log.info("핸드폰 인증 cmd: {}", cmd.toString());

        String authCode = smsFindPort.findSms(cmd.phoneNumber());

        if(authCode == null || !authCode.equals(cmd.authcode())){
            throw new AuthServiceException(ErrorCode.AUTHCODE_NOT_MATCH);
        }

        smsDeletePort.deleteSms(cmd.phoneNumber());
    }
}
