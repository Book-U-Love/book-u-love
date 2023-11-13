package org.bookulove.user.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.UseCase;
import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.error.ErrorCode;
import org.bookulove.common.feignclient.book.LibraryFindListRes;
import org.bookulove.common.feignclient.book.LibraryFindRes;
import org.bookulove.common.feignclient.user.UserFindInfoRes;
import org.bookulove.common.util.AuthUtil;
import org.bookulove.user.application.port.in.UserFindListUseCase;
import org.bookulove.user.application.port.out.UserFindLibraryListPort;
import org.bookulove.user.application.port.out.UserFindListPort;
import org.bookulove.user.application.port.out.UserFindPort;
import org.bookulove.user.domain.UserDomain;
import org.bookulove.user.domain.UserFindInfoListDomain;
import org.bookulove.user.exception.UserServiceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserFindInfoListService implements UserFindListUseCase {

    private final AuthUtil authUtil;
    private final UserFindListPort userFindListPort;
    private final UserFindLibraryListPort userFindLibraryListPort;

    @Override
    public UserFindInfoListDomain findInfoList() {
        String token = authUtil.getTokenByHeader();

        List<UserDomain> userList = userFindListPort.findUserList();

        ApiData<LibraryFindListRes> libraryList = userFindLibraryListPort.findLibraryList(token);
        if(libraryList.status() != 200){
            throw new UserServiceException(ErrorCode.LIBRARY_NOT_FOUND);
        }

        List<UserFindInfoRes> userFindInfoResList = new ArrayList<>();
        for(int i=0; i<userList.size(); ++i){
            userFindInfoResList.add(UserFindInfoRes.of(userList.get(i).id(), userList.get(i).loginId(), userList.get(i).nickname(), userList.get(i).phoneNumber(), userList.get(i).allowNoti(),
                    libraryList.data().libraryFindResList().get(i).libraryName(), libraryList.data().libraryFindResList().get(i).lat(), libraryList.data().libraryFindResList().get(i).lng()));
        }

        UserFindInfoListDomain userFindInfoListDomain = UserFindInfoListDomain.of(userFindInfoResList);
        log.info("유저들 조회 domain: {}", userFindInfoListDomain);

        return userFindInfoListDomain;
    }


}
