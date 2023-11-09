package org.bookulove.user.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.UseCase;
import org.bookulove.common.feignclient.user.UserFindRes;
import org.bookulove.common.util.AuthUtil;
import org.bookulove.user.application.port.in.UserFindUseCase;
import org.bookulove.user.application.port.out.UserFindPort;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserFindService implements UserFindUseCase {

    private final AuthUtil authUtil;
    private final UserFindPort userFindPort;

    @Override
    public UserFindRes findUser() {
        Long userId = authUtil.getUserIdByHeader();
        return userFindPort.findUser(userId);
    }
}
