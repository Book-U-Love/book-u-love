package org.bookulove.user.adapter.out.service.feign;

import jakarta.validation.Valid;
import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.feignclient.book.LibraryCreateReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "LibraryCreateFeign", url = "${domain.url}" + "/api/book-service/libraries")
public interface LibraryCreateFeign {

    @PostMapping
    ApiData<?> createLibrary(@RequestBody @Valid LibraryCreateReq libraryRegistReq);

}
