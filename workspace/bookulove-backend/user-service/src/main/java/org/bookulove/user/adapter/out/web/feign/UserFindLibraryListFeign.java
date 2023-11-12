package org.bookulove.user.adapter.out.web.feign;

import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.feignclient.book.LibraryFindListRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "LibraryFindListFeign", url = "${domain.url}" + "/api/book-service/libraries")
public interface UserFindLibraryListFeign {

    @GetMapping("/list")
    ApiData<LibraryFindListRes> findLibraryList(@RequestHeader("Authorization")String authHeader);
}
