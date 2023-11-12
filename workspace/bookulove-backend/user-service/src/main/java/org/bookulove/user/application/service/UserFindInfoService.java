package org.bookulove.user.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.UseCase;
import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.error.ErrorCode;
import org.bookulove.common.feignclient.book.LibraryFindRes;
import org.bookulove.common.feignclient.user.UserFindInfoRes;
import org.bookulove.common.util.AuthUtil;
import org.bookulove.user.application.port.in.UserFindUseCase;
import org.bookulove.user.application.port.out.UserFindLibraryPort;
import org.bookulove.user.application.port.out.UserFindPort;
import org.bookulove.user.domain.UserDomain;
import org.bookulove.user.exception.UserServiceException;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserFindInfoService implements UserFindUseCase {

    private final AuthUtil authUtil;
    private final UserFindPort userFindPort;
    private final UserFindLibraryPort userFindLibraryPort;

    @Override
    public UserFindInfoRes findUser() {
        Long userId = authUtil.getUserIdByHeader();

        UserDomain userDomain = userFindPort.findUser(userId);

        String token = authUtil.getTokenByHeader();

        ApiData<LibraryFindRes> libraryFindRes = userFindLibraryPort.findLibrary(token);
        if(libraryFindRes.status() != 200){
            throw new UserServiceException(ErrorCode.LIBRARY_NOT_FOUND);
        }

        return UserFindInfoRes.of(userDomain.id(),
                userDomain.loginId(),
                userDomain.nickname(),
                userDomain.phoneNumber(),
                userDomain.allowNoti(),
                libraryFindRes.data().libraryName(),
                libraryFindRes.data().lat(),
                libraryFindRes.data().lng());
    }

    @Override
    public UserFindInfoRes findUser(Long userId) {
        UserDomain userDomain = userFindPort.findUser(userId);

        String token = authUtil.getTokenByHeader();

        ApiData<LibraryFindRes> libraryFindRes = userFindLibraryPort.findLibrary(token);
        if(libraryFindRes.status() != 200){
            throw new UserServiceException(ErrorCode.LIBRARY_NOT_FOUND);
        }

        return UserFindInfoRes.of(userDomain.id(),
                userDomain.loginId(),
                userDomain.nickname(),
                userDomain.phoneNumber(),
                userDomain.allowNoti(),
                libraryFindRes.data().libraryName(),
                libraryFindRes.data().lat(),
                libraryFindRes.data().lng());
    }
}
