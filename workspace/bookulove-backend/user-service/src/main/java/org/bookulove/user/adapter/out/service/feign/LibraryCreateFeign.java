package org.bookulove.user.adapter.out.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "LibraryCreateFeign", url = "${DOMAIN-URL}" + "/api/book-service/libraries")
public interface LibraryCreateFeign {

//    @PostMapping
//    Response

}
