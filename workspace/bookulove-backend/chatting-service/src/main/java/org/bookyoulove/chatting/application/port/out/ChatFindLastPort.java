package org.bookyoulove.chatting.application.port.out;

import org.bookyoulove.chatting.domain.ChattingDomain;

public interface ChatFindLastPort {

    ChattingDomain findLastChat(Long roomId);
}
