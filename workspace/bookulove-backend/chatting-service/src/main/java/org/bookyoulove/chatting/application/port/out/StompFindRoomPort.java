package org.bookyoulove.chatting.application.port.out;

import org.bookyoulove.chatting.domain.ChattingRoomDomain;

public interface StompFindRoomPort {

    ChattingRoomDomain findRoom(Long roomId, Long userId);
}
