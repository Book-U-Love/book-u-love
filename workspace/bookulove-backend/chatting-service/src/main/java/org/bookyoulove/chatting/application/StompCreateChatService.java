package org.bookyoulove.chatting.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.UseCase;
import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.error.ErrorCode;
import org.bookulove.common.feignclient.user.UserFindRes;
import org.bookyoulove.chatting.adapter.out.web.StompFindUserAdapter;
import org.bookyoulove.chatting.application.port.in.StompCreateChatUseCase;
import org.bookyoulove.chatting.application.port.in.dto.request.StompCreateChatCmd;
import org.bookyoulove.chatting.application.port.out.StompFindConnPort;
import org.bookyoulove.chatting.application.port.out.StompFindRoomPort;
import org.bookyoulove.chatting.global.exception.ChatServiceException;
import org.bookyoulove.chatting.global.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class StompCreateChatService implements StompCreateChatUseCase {

    private final JwtUtil jwtUtil;
    private final StompFindRoomPort stompFindRoomPort;
    private final StompFindConnPort stompFindConnPort;
    private final StompFindUserAdapter stompFindUserAdapter;
    private final SimpMessageSendingOperations sendingOperations;

    @Override
    public void createChat(StompCreateChatCmd cmd) {
        log.info("채팅 생성 cmd: {}", cmd);

        Long userId = jwtUtil.extractId(cmd.token());
        log.info("현재접속유저 id: {}", userId);

        // TODO: 2023-11-09 채팅방 생성 로직이 먼저
//        ChattingRoomDomain chattingRoomDomain = stompFindRoomPort.findRoom(cmd.roomId());
//
//        Long targetId = Objects.equals(userId, chattingRoomDomain.buyerId()) ? userId : chattingRoomDomain.sellerId();
//        log.info("상대방 아이디: {}", targetId);

        ApiData<UserFindRes> userRes = stompFindUserAdapter.findUser(cmd.token());
        if(userRes.status() != HttpStatus.OK.value()){
            throw new ChatServiceException(ErrorCode.USER_NOT_FOUND);
        }

        log.info("내 정보 res: {}", userRes.data());

        // TODO: 2023-11-09 채팅방 생성 로직이 먼저 
//        if(Objects.equals(stompFindConnPort.findConn(targetId), cmd.roomId().toString())){
//            sendingOperations.convertAndSend("sub/" + cmd.roomId().toString(), );
//        }

    }
}
