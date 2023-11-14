package org.bookyoulove.chatting.application.port.out;

import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.feignclient.book.BookRes;

public interface ChatFindBookPort {

    ApiData<BookRes> findBook(String token, Long buId);
}
