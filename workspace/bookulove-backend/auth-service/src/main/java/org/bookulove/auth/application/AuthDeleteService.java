package org.bookulove.auth.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.auth.application.port.in.AuthDeleteUseCase;
import org.bookulove.auth.application.port.out.AuthDeletePort;
import org.bookulove.common.annotation.UseCase;
import org.bookulove.common.util.AuthUtil;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthDeleteService implements AuthDeleteUseCase {

    private final AuthUtil authUtil;
    private final AuthDeletePort authDeletePort;

    @Override
    public void deleteAuth() {
        Long userId = authUtil.getUserIdByHeader();

        authDeletePort.deleteAuth(userId);
    }
}
