package org.bookulove.user.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.UseCase;
import org.bookulove.common.util.AuthUtil;
import org.bookulove.user.application.port.in.UserUpdateUseCase;
import org.bookulove.user.application.port.in.dto.command.UserUpdateCmd;
import org.bookulove.user.application.port.out.UserUpdateNicknamePort;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class UserUpdateService implements UserUpdateUseCase {

    private final AuthUtil authUtil;
    private final UserUpdateNicknamePort userUpdateNicknamePort;

    @Override
    public void updateUser(UserUpdateCmd cmd) {
        log.info("회원정보수정 cmd: {}", cmd);
        Long userId = authUtil.getUserIdByHeader();

        // TODO: 2023-11-03 library feign client need

        userUpdateNicknamePort.updateNickname(userId, cmd.nickname());
    }
}
