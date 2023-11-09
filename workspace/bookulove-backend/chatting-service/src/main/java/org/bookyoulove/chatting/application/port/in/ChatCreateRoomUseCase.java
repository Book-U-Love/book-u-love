package org.bookyoulove.chatting.application.port.in;

import org.bookyoulove.chatting.application.port.in.dto.request.ChatCreateRoomCmd;
import org.bookyoulove.chatting.domain.ChattingRoomDomain;

public interface ChatCreateRoomUseCase {

    ChattingRoomDomain createRoom(ChatCreateRoomCmd cmd);
}
