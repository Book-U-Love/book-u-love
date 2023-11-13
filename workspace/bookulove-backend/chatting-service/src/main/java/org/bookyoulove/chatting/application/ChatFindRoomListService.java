package org.bookyoulove.chatting.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.UseCase;
import org.bookulove.common.util.AuthUtil;
import org.bookyoulove.chatting.application.port.in.ChatFindRoomListUseCase;
import org.bookyoulove.chatting.application.port.out.ChatFindLastPort;
import org.bookyoulove.chatting.application.port.out.ChatFindRoomListPort;
import org.bookyoulove.chatting.domain.ChattingDomain;
import org.bookyoulove.chatting.domain.ChattingRoomDomain;
import org.bookyoulove.chatting.domain.ChattingRoomListDomain;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatFindRoomListService implements ChatFindRoomListUseCase {

    private final ChatFindLastPort chatFindLastPort;
    private final ChatFindRoomListPort chatFindRoomListPort;

    @Override
    public ChattingRoomListDomain findRoomList(Long userId) {

        List<ChattingRoomDomain> roomDomainList = chatFindRoomListPort.findRoomList(userId);
        List<ChattingDomain> chattingDomainList =
                roomDomainList.stream()
                .map(p-> chatFindLastPort.findLastChat(p.roomId()))
                .collect(Collectors.toList());

        log.info("마지막 채팅 리스트 domain: {}", chattingDomainList);

        return null;
    }
}
