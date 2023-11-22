package org.bookyoulove.chatting.application.port.out;

public interface StompCreateConnPort {

    void createConn(Long userId, String roomId);
}
