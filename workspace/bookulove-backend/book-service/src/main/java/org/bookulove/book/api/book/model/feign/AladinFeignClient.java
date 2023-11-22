package org.bookulove.book.api.book.model.feign;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "AladinFeignClient", url = "${custom.aladin.url.lookUp}")
public interface AladinFeignClient {

    @GetMapping()
    Response aladinSearch(
            @RequestParam(name = "ttbkey") String ttbkey,
            @RequestParam(name = "itemIdType")  String itemIdType,
            @RequestParam(name = "itemId")  String itemId,
            @RequestParam(name = "output")  String output,
            @RequestParam(name = "version")  String version
    );

}
