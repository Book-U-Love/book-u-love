package org.bookyoulove.chatting.adapter.out.persist;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.PersistenceAdapter;
import org.bookulove.common.error.ErrorCode;
import org.bookyoulove.chatting.adapter.out.persist.repository.RoomRepository;
import org.bookyoulove.chatting.application.port.out.StompFindRoomPort;
import org.bookyoulove.chatting.domain.ChattingRoomDomain;
import org.bookyoulove.chatting.global.exception.ChatServiceException;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class StompFindRoomAdapter implements StompFindRoomPort {

    private final RoomRepository roomRepository;

    @Override
    public ChattingRoomDomain findRoom(Long roomId) {
        ChattingRoomDomain chattingRoomDomain = org.bookyoulove.chatting.domain.ChattingRoomDomain.of(roomRepository.findById(roomId).orElseThrow(
                () -> new ChatServiceException(ErrorCode.ROOM_NOT_FOUND)
        ));

        log.info("접속중인 방 domain: {}", chattingRoomDomain);

        return chattingRoomDomain;
    }
}
