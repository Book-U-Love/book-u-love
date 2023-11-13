package org.bookyoulove.chatting.application.port.in;

import org.bookyoulove.chatting.domain.ChattingRoomListDomain;

public interface ChatFindRoomListUseCase {

    ChattingRoomListDomain findRoomList(Long userId);
}


