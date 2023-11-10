package org.bookulove.user.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.error.ErrorCode;
import org.bookulove.common.feignclient.book.LibraryCreateReq;
import org.bookulove.user.application.port.in.UserCreateUseCase;
import org.bookulove.user.application.port.in.dto.command.UserCreateCmd;
import org.bookulove.user.application.port.out.UserCreateLibraryPort;
import org.bookulove.user.application.port.out.UserCreatePort;
import org.bookulove.common.annotation.UseCase;
import org.bookulove.user.domain.UserCreateDomain;
import org.bookulove.user.exception.UserServiceException;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class UserCreateService implements UserCreateUseCase {

    private final UserCreatePort userCreatePort;
    private final UserCreateLibraryPort libraryCreatePort;

    @Override
    public void createUser(UserCreateCmd cmd) {
        log.info("회원가입 cmd: {}", cmd);

        UserCreateDomain userCreateDomain =
                userCreatePort.createUser(cmd.Id(),
                        cmd.password(),
                        cmd.phoneNumber(),
                        cmd.nickname());

        ApiData<String> td = libraryCreatePort.createLibrary(LibraryCreateReq.of(userCreateDomain.id(), cmd.libraryName(), cmd.lat(), cmd.lng()));
        log.info("Feign result: {}", td);

        if (td.status() != 200) {
            throw new UserServiceException(ErrorCode.LIBRARY_CREATE_ERROR, td.data());
        }
    }
}
