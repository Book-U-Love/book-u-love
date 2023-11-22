package org.bookulove.user.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.UseCase;
import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.error.ErrorCode;
import org.bookulove.common.feignclient.book.LibraryUpdateReq;
import org.bookulove.common.util.AuthUtil;
import org.bookulove.user.application.port.in.UserUpdateUseCase;
import org.bookulove.user.application.port.in.dto.command.UserUpdateCmd;
import org.bookulove.user.application.port.out.UserUpdateLibraryPort;
import org.bookulove.user.application.port.out.UserUpdateNicknamePort;
import org.bookulove.user.exception.UserServiceException;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class UserUpdateService implements UserUpdateUseCase {

    private final AuthUtil authUtil;
    private final UserUpdateNicknamePort userUpdateNicknamePort;
    private final UserUpdateLibraryPort userUpdateLibraryPort;

    @Override
    public void updateUser(UserUpdateCmd cmd) {
        log.info("회원정보수정 cmd: {}", cmd);

        Long userId = authUtil.getUserIdByHeader();
        String token = authUtil.getTokenByHeader();

        ApiData<String> libraryRes =
                userUpdateLibraryPort.updateLibrary(token, LibraryUpdateReq.of(cmd.libraryName(), cmd.lat(), cmd.lng()));

        if(libraryRes.status() != 200){
            throw new UserServiceException(ErrorCode.LIBRARY_NOT_FOUND);
        }

        userUpdateNicknamePort.updateNickname(userId, cmd.nickname());
    }
}
