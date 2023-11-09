package org.bookyoulove.chatting.adapter.out.web.feign;

import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.feignclient.user.UserFindRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "UserFindFeign", url = "${domain.url}" + "/api/user-service/users")
public interface StompFindUserFeign {

    @GetMapping
    ApiData<UserFindRes> findUser(@RequestHeader("Authorization") String headers);

}
