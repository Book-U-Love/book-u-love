package org.bookyoulove.chatting.application.port.out;

import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.feignclient.user.UserFindInfoRes;

public interface StompFindUserPort {

    ApiData<UserFindInfoRes> findUser(String authHeader);

    ApiData<UserFindInfoRes> findUser(String authHeader, Long userId);
}
