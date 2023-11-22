package org.bookulove.user.adapter.out.web.feign;

import jakarta.validation.Valid;
import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.feignclient.book.LibraryUpdateReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "LibraryUpdateFeign", url = "${domain.url}" + "/api/book-service/libraries")
public interface UserUpdateLibraryFeign {

    @PutMapping
    ApiData<String> updateLibrary(@RequestHeader("Authorization")String authHeader, @RequestBody @Valid LibraryUpdateReq req);

}
