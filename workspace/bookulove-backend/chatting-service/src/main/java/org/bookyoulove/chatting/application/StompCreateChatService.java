package org.bookyoulove.chatting.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.UseCase;
import org.bookyoulove.chatting.application.port.in.StompCreateChatUseCase;
import org.bookyoulove.chatting.application.port.in.dto.request.StompCreateChatCmd;
import org.bookyoulove.chatting.application.port.out.StompFindConnPort;
import org.bookyoulove.chatting.application.port.out.StompFindRoomPort;
import org.bookyoulove.chatting.domain.ChattingRoomDomain;
import org.bookyoulove.chatting.global.util.JwtUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class StompCreateChatService implements StompCreateChatUseCase {

    private final JwtUtil jwtUtil;
    private final StompFindRoomPort stompFindRoomPort;
    private final StompFindConnPort stompFindConnPort;

    @Override
    public void createChat(StompCreateChatCmd cmd) {
        log.info("채팅 생성 cmd: {}", cmd);

        Long userId = jwtUtil.extractId(cmd.token());
        log.info("현재접속유저 id: {}", userId);

        ChattingRoomDomain chattingRoomDomain = stompFindRoomPort.findRoom(cmd.roomId());

        Long targetId = Objects.equals(userId, chattingRoomDomain.buyerId()) ? userId : chattingRoomDomain.sellerId();
        log.info("상대방 아이디: {}", targetId);

        if(Objects.equals(stompFindConnPort.findConn(targetId), cmd.roomId().toString())){

        }

    }
}
