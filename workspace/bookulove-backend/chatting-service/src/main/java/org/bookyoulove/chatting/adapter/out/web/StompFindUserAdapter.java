package org.bookyoulove.chatting.adapter.out.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.WebAdapter;
import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.feignclient.user.UserFindRes;
import org.bookyoulove.chatting.adapter.out.web.feign.StompFindUserFeign;
import org.bookyoulove.chatting.application.port.out.StompFindUserPort;
import org.springframework.http.HttpHeaders;

import java.util.Map;

@Slf4j
@WebAdapter
@RequiredArgsConstructor
public class StompFindUserAdapter implements StompFindUserPort {

    private final StompFindUserFeign stompFindUserFeign;

    @Override
    public ApiData<UserFindRes> findUser(String authHeader) {
        return stompFindUserFeign.findUser(authHeader);
    }
}
