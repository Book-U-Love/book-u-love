package org.bookyoulove.chatting.application.port.in;

import org.bookyoulove.chatting.domain.ChattingListDomain;

public interface ChatFindListUseCase {

    ChattingListDomain findChatList(Long userId, String token, Long roomId);
}
