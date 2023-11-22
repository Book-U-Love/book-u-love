package org.bookyoulove.chatting.application.port.out;

import org.bookyoulove.chatting.domain.ChattingRoomDomain;

public interface ChatCreateRoomPort {

    ChattingRoomDomain createRoom(Long buId, Long sellerId, Long buyerId, Long myId);
}
