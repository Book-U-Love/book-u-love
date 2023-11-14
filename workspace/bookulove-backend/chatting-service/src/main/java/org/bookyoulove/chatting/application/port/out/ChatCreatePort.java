package org.bookyoulove.chatting.application.port.out;

public interface ChatCreatePort {

    void createChat(String content, Long writerId, Long readCount, Long roomId);
}
