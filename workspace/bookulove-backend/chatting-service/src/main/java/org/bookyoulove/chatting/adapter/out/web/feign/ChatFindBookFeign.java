package org.bookyoulove.chatting.adapter.out.web.feign;
import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.feignclient.book.BookRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "ChatFindBookFeign", url = "${domain.url}" + "/api/book-service/relations")
public interface ChatFindBookFeign {

    @GetMapping("/{buId}")
    ApiData<BookRes> findBook(@RequestHeader("Authorization") String authHeader, @PathVariable(name = "buId") Long buId);
}
