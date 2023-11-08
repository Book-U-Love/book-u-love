package org.bookyoulove.chatting.application.port.in;

import org.bookyoulove.chatting.application.port.in.dto.request.StompCreateChatCmd;

public interface StompCreateChatUseCase {

    void createChat(StompCreateChatCmd cmd);
}
