package org.bookyoulove.chatting.application.port.out;

import org.bookyoulove.chatting.domain.ChattingDomain;

import java.util.List;

public interface ChatFindListPort {

    List<ChattingDomain> findChatList(Long roomId, Long userId);
}
