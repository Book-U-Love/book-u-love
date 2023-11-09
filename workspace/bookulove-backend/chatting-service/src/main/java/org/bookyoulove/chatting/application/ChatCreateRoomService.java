package org.bookyoulove.chatting.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.UseCase;
import org.bookulove.common.util.AuthUtil;
import org.bookyoulove.chatting.application.port.in.ChatCreateRoomUseCase;
import org.bookyoulove.chatting.application.port.in.dto.request.ChatCreateRoomCmd;
import org.bookyoulove.chatting.application.port.out.ChatCreateRoomPort;
import org.bookyoulove.chatting.domain.ChattingRoomDomain;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class ChatCreateRoomService implements ChatCreateRoomUseCase {

    private final AuthUtil authUtil;
    private final ChatCreateRoomPort chatCreateRoomPort;

    @Override
    public ChattingRoomDomain createRoom(ChatCreateRoomCmd cmd) {
        log.info("채팅방 생성 cmd: {}", cmd);

        Long userId = authUtil.getUserIdByHeader();

        return chatCreateRoomPort.createRoom(cmd.buId(), userId, cmd.sellerId());
    }
}
