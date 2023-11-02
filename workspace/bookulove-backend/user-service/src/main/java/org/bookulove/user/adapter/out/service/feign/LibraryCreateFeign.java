package org.bookulove.user.adapter.out.service.feign;

import jakarta.validation.Valid;
import org.bookyoulove.common.api.response.ApiData;
import org.bookyoulove.common.feignclient.book.LibraryCreateReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "LibraryCreateFeign", url = "${DOMAIN-URL}" + "/api/book-service/libraries")
public interface LibraryCreateFeign {

    @PostMapping
    ApiData<?> createLibrary(@RequestBody @Valid LibraryCreateReq libraryRegistReq);

}
