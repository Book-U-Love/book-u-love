package org.bookyoulove.chatting.application.port.out;

import org.bookyoulove.chatting.domain.ChattingRoomDomain;

import java.util.List;

public interface ChatFindRoomListPort {

    List<ChattingRoomDomain> findRoomList(Long userId);
}
