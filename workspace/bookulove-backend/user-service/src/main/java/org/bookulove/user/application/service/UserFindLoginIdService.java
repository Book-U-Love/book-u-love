package org.bookulove.user.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.UseCase;
import org.bookulove.common.error.ErrorCode;
import org.bookulove.user.application.port.in.UserFindLoginIdUseCase;
import org.bookulove.user.application.port.out.UserFindLoginIdPort;
import org.bookulove.user.exception.UserServiceException;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserFindLoginIdService implements UserFindLoginIdUseCase {

    private final UserFindLoginIdPort userFindLoginIdPort;

    @Override
    public void findLoginId(String loginId) {
        log.info("아이디 중복검사 cmd: {}", loginId);

        if(userFindLoginIdPort.findLoginId(loginId)){
            throw new UserServiceException(ErrorCode.DUPLICATE_LOGIN_ID);
        }
    }
}
