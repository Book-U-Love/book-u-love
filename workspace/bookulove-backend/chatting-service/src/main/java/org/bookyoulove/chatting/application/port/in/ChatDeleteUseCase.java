package org.bookyoulove.chatting.application.port.in;

import org.bookyoulove.chatting.global.filter.SecurityDto;

public interface ChatDeleteUseCase {

    void exitRoom(Long userId);
}
