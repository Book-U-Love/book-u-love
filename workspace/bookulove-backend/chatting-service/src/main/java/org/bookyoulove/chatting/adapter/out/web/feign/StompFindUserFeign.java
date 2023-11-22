package org.bookyoulove.chatting.adapter.out.web.feign;

import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.feignclient.user.UserFindInfoRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "UserFindFeign", url = "${domain.url}" + "/api/user-service/users")
public interface StompFindUserFeign {

    @GetMapping
    ApiData<UserFindInfoRes> findUser(@RequestHeader("Authorization") String authHeader);

    @GetMapping("/{userId}")
    ApiData<UserFindInfoRes> findUser(@RequestHeader("Authorization") String authHeader, @PathVariable(name = "userId") Long userId);
}
