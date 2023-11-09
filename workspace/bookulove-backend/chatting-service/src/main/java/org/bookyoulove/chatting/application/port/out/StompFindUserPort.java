package org.bookyoulove.chatting.application.port.out;

import org.bookulove.common.api.response.ApiData;

public interface StompFindUserPort {

    ApiData<?> findUser(String authHeader);
}
