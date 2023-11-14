package org.bookyoulove.chatting.adapter.out.persist;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.PersistenceAdapter;
import org.bookyoulove.chatting.adapter.out.persist.repository.query.ChatQueryRepository;
import org.bookyoulove.chatting.application.port.out.ChatFindUnreadCountPort;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class ChatFindUnreadCountAdapter implements ChatFindUnreadCountPort {

    private final ChatQueryRepository chatQueryRepository;

    @Override
    public Long unreadCount(Long userId, Long roomId) {
        Long unreadCount = chatQueryRepository.findUnreadCount(userId, roomId);
        if(unreadCount == null){
            unreadCount = 0L;
        }

        return unreadCount;
    }
}
