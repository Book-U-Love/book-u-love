package org.bookyoulove.chatting.adapter.out.persist;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.PersistenceAdapter;
import org.bookulove.common.error.ErrorCode;
import org.bookyoulove.chatting.adapter.out.persist.entity.ChattingEntity;
import org.bookyoulove.chatting.adapter.out.persist.entity.ChattingRoomEntity;
import org.bookyoulove.chatting.adapter.out.persist.repository.ChatRepository;
import org.bookyoulove.chatting.adapter.out.persist.repository.RoomRepository;
import org.bookyoulove.chatting.application.port.out.ChatCreatePort;
import org.bookyoulove.chatting.global.exception.ChatServiceException;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class ChatCraeteAdapter implements ChatCreatePort {

    private final RoomRepository roomRepository;
    private final ChatRepository chatRepository;

    @Override
    public void createChat(String content, Long writerId, Long readCount, Long roomId) {

        ChattingRoomEntity chattingRoomEntity = roomRepository.findById(roomId).orElseThrow(
                () -> new ChatServiceException(ErrorCode.ROOM_NOT_FOUND)
        );

        ChattingEntity chattingEntity =
                ChattingEntity.of(
                        content,
                        writerId,
                        readCount,
                        chattingRoomEntity
                );

        chatRepository.save(chattingEntity);
    }
}
