package org.bookulove.api.book.model.feign;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "AladinFeignClient", url = "${custom.aladin.url.lookUp}")
public interface AladinFeignClient {

    @GetMapping()
    Response aladinSearch(
            @RequestParam String ttbkey,
            @RequestParam String itemIdType,
            @RequestParam String itemId,
            @RequestParam String output,
            @RequestParam String version
    );

}
