package org.bookulove.user.adapter.out.web.feign;

import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.feignclient.book.LibraryFindRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "LigraryFindFeign", url = "${domain.url}" + "/api/book-service/libraries")
public interface UserFindLibraryFeign {

    @GetMapping
    ApiData<LibraryFindRes> findLibrary(@RequestHeader("Authorization")String authHeader);
}
