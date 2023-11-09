package org.bookyoulove.chatting.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.UseCase;
import org.bookulove.common.util.AuthUtil;
import org.bookyoulove.chatting.application.port.in.ChatFindRoomListUseCase;
import org.bookyoulove.chatting.application.port.out.ChatFindRoomListPort;
import org.bookyoulove.chatting.domain.ChattingRoomDomain;
import org.bookyoulove.chatting.domain.ChattingRoomListDomain;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatFindRoomListService implements ChatFindRoomListUseCase {

    private final AuthUtil authUtil;
    private final ChatFindRoomListPort chatFindRoomListPort;

    @Override
    public ChattingRoomListDomain findRoomList() {
        Long userId = authUtil.getUserIdByHeader();

        List<ChattingRoomDomain> roomList = chatFindRoomListPort.findRoomList(userId);

    }
}
