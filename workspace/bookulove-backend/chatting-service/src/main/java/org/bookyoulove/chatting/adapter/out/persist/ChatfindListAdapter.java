package org.bookyoulove.chatting.adapter.out.persist;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.PersistenceAdapter;
import org.bookulove.common.error.ErrorCode;
import org.bookyoulove.chatting.adapter.out.persist.entity.ChattingEntity;
import org.bookyoulove.chatting.adapter.out.persist.repository.ChatRepository;
import org.bookyoulove.chatting.adapter.out.persist.repository.RoomRepository;
import org.bookyoulove.chatting.application.port.out.ChatFindListPort;
import org.bookyoulove.chatting.domain.ChattingDomain;
import org.bookyoulove.chatting.global.exception.ChatServiceException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class ChatfindListAdapter implements ChatFindListPort {

    private final RoomRepository roomRepository;

    @Override
    public List<ChattingDomain> findChatList(Long roomId, Long userId) {

        return roomRepository.findById(roomId).orElseThrow(
                        () -> new ChatServiceException(ErrorCode.ROOM_NOT_FOUND)
                )
                .getChatting()
                .stream()
                .map(p->{
                    p.updateReadCount(userId);
                    return ChattingDomain.of(p);
                })
                .collect(Collectors.toList());
    }
}
