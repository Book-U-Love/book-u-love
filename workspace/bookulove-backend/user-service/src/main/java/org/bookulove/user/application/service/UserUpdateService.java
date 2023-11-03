package org.bookulove.user.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.UseCase;
import org.bookulove.common.util.AuthUtil;
import org.bookulove.user.adapter.out.persistence.UserUpdateAdapter;
import org.bookulove.user.adapter.out.persistence.entity.UserEntity;
import org.bookulove.user.application.port.in.UserUpdateUseCase;
import org.bookulove.user.application.port.in.dto.command.UserUpdateCmd;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class UserUpdateService implements UserUpdateUseCase {

    private final AuthUtil authUtil;
    private final UserUpdateAdapter userUpdateAdapter;

    @Override
    public void updateUser(UserUpdateCmd cmd) {
        log.info("회원정보수정 cmd: {}", cmd);
        Long userId = authUtil.getUserIdByHeader();

        // TODO: 2023-11-03 library feign client need

        userUpdateAdapter.updateUser(userId, cmd.password(), cmd.nickname());
    }
}
