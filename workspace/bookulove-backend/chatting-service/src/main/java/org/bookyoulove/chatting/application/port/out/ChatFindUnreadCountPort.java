package org.bookyoulove.chatting.application.port.out;

public interface ChatFindUnreadCountPort {

    Long unreadCount(Long userId, Long roomId);
}
