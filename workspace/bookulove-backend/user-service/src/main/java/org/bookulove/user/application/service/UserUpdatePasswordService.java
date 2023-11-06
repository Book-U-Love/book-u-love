package org.bookulove.user.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.UseCase;
import org.bookulove.common.error.ErrorCode;
import org.bookulove.common.util.AuthUtil;
import org.bookulove.user.application.port.in.UserUpdatePasswordUseCase;
import org.bookulove.user.application.port.in.dto.command.UserUpdatePasswordCmd;
import org.bookulove.user.application.port.out.UserFindPasswordPort;
import org.bookulove.user.application.port.out.UserUpdatePasswordPort;
import org.bookulove.user.exception.UserServiceException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class UserUpdatePasswordService implements UserUpdatePasswordUseCase {

    private final AuthUtil authUtil;
    private final UserFindPasswordPort userFindPasswordPort;
    private final BCryptPasswordEncoder encoder;
    private final UserUpdatePasswordPort userUpdatePasswordPort;

    @Override
    public void updatePassword(UserUpdatePasswordCmd cmd) {
        log.info("비밀번호 변경 cmd: {}", cmd);

        Long userId = authUtil.getUserIdByHeader();

        String password = userFindPasswordPort.findPassword(userId);
        log.info("저장된 비밀번호: {}", password);

        if(!encoder.matches(cmd.oldPassword(), password)){
            throw new UserServiceException(ErrorCode.PASSWORD_NOT_MATCH);
        }

        userUpdatePasswordPort.updatePassword(userId, cmd.newPassword());
    }
}
