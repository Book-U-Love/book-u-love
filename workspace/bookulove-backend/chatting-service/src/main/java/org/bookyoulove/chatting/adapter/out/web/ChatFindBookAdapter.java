package org.bookyoulove.chatting.adapter.out.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.Adapter;
import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.feignclient.book.BookRes;
import org.bookyoulove.chatting.adapter.out.web.feign.ChatFindBookFeign;
import org.bookyoulove.chatting.application.port.out.ChatFindBookPort;

@Slf4j
@Adapter
@RequiredArgsConstructor
public class ChatFindBookAdapter implements ChatFindBookPort {

    private final ChatFindBookFeign chatFindBookFeign;

    @Override
    public ApiData<BookRes> findBook(String token, Long buId) {
        ApiData<BookRes> bookResApiData = chatFindBookFeign.findBook(token, buId);
        log.info("bookRes: {}", bookResApiData);

        return bookResApiData;
    }
}
