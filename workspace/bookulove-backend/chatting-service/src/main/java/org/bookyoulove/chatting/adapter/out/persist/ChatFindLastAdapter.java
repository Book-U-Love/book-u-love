package org.bookyoulove.chatting.adapter.out.persist;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.PersistenceAdapter;
import org.bookyoulove.chatting.adapter.out.persist.repository.ChatRepository;
import org.bookyoulove.chatting.adapter.out.persist.repository.query.ChatQueryRepository;
import org.bookyoulove.chatting.application.port.out.ChatFindLastPort;
import org.bookyoulove.chatting.domain.ChattingDomain;

import java.util.Optional;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class ChatFindLastAdapter implements ChatFindLastPort {

    private final ChatQueryRepository chatQueryRepository;

    @Override
    public ChattingDomain findLastChat(Long roomId) {

        Optional<ChattingDomain> chattingDomain = chatQueryRepository.findLastChat(roomId);
        return chattingDomain.orElse(null);

    }

}
