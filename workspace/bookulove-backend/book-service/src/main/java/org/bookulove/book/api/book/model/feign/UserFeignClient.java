package org.bookulove.book.api.book.model.feign;

import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.feignclient.user.UserFindInfoRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "UserFeignClient", url = "${domain.url}" + "/api/user-service/users")
public interface UserFeignClient {

    @GetMapping("/{userId}")
    ApiData<UserFindInfoRes> findUserByUserId(@RequestHeader("Authorization")String authHeader, @PathVariable(name = "userId") Long userId);
}
