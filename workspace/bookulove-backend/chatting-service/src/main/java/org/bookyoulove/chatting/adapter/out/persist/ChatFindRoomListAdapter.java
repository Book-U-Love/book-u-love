package org.bookyoulove.chatting.adapter.out.persist;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.PersistenceAdapter;
import org.bookyoulove.chatting.adapter.out.persist.entity.ChattingRoomEntity;
import org.bookyoulove.chatting.adapter.out.persist.repository.RoomRepository;
import org.bookyoulove.chatting.application.port.out.ChatFindRoomListPort;
import org.bookyoulove.chatting.domain.ChattingRoomDomain;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class ChatFindRoomListAdapter implements ChatFindRoomListPort {

    private final RoomRepository roomRepository;

    @Override
    public List<ChattingRoomDomain> findRoomList(Long userId) {

        List<ChattingRoomDomain> chattingRoomDomainList =
                roomRepository.findAllBySellerIdOrBuyerId(userId, userId)
                        .stream()
                        .map(ChattingRoomDomain::of)
                        .collect(Collectors.toList());

        log.info("채팅방 리스트 domain: {}", chattingRoomDomainList);

        return chattingRoomDomainList;
    }


}
