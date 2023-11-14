package org.bookulove.user.adapter.out.web.feign;

import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.feignclient.book.LibraryFindListRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "RelationFindListFeign", url = "${domain.url}" + "/api/book-service/relations")
public interface UserFindBookCountFeign {

    @GetMapping("/count/{userId}")
    ApiData<Integer> findLibraryList(@RequestHeader("Authorization")String authHeader, @PathVariable(name = "userId") Long userId);
}
