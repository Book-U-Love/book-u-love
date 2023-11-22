package org.bookyoulove.chatting.adapter.out.persist;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.PersistenceAdapter;
import org.bookyoulove.chatting.adapter.out.persist.entity.ChattingRoomEntity;
import org.bookyoulove.chatting.adapter.out.persist.repository.RoomRepository;
import org.bookyoulove.chatting.application.port.out.ChatCreateRoomPort;
import org.bookyoulove.chatting.domain.ChattingRoomDomain;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class ChatCreateRoomAdapter implements ChatCreateRoomPort {

    private final RoomRepository roomRepository;

    @Override
    public ChattingRoomDomain createRoom(Long buId, Long sellerId, Long buyerId, Long myId) {
        ChattingRoomDomain roomDomain =
                ChattingRoomDomain.of(roomRepository.findByBuIdAndSellerIdAndBuyerId(buId, sellerId, buyerId)
                        .orElseGet(
                                () -> roomRepository.save(ChattingRoomEntity.of(buId, sellerId, buyerId))
                        ), myId);

        log.info("채팅방 정보 domain: {}", roomDomain);
        return roomDomain;
    }
}
