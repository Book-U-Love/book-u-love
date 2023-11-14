package org.bookyoulove.chatting.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.UseCase;
import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.error.ErrorCode;
import org.bookulove.common.feignclient.book.BookRes;
import org.bookulove.common.feignclient.user.UserFindInfoRes;
import org.bookyoulove.chatting.adapter.out.persist.entity.ChattingEntity;
import org.bookyoulove.chatting.adapter.out.web.StompFindUserAdapter;
import org.bookyoulove.chatting.application.port.in.StompCreateChatUseCase;
import org.bookyoulove.chatting.application.port.in.dto.request.StompCreateChatCmd;
import org.bookyoulove.chatting.application.port.out.*;
import org.bookyoulove.chatting.domain.ChattingRoomDomain;
import org.bookyoulove.chatting.domain.ChattingRoomInfoDomain;
import org.bookyoulove.chatting.domain.StompChatDomain;
import org.bookyoulove.chatting.global.exception.ChatServiceException;
import org.bookyoulove.chatting.global.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class StompCreateChatService implements StompCreateChatUseCase {

    private final JwtUtil jwtUtil;
    private final ChatCreatePort chatCreatePort;
    private final ChatFindBookPort chatFindBookPort;
    private final StompFindRoomPort stompFindRoomPort;
    private final StompFindConnPort stompFindConnPort;
    private final StompFindUserPort stompFindUserPort;
    private final SimpMessageSendingOperations sendingOperations;
    private final ChatFindUnreadCountPort chatFindUnreadCountPort;

    @Override
    public void createChat(StompCreateChatCmd cmd) {
        log.info("채팅 생성 cmd: {}", cmd);

        Long userId = jwtUtil.extractId(cmd.token());
        log.info("현재접속유저 id: {}", userId);

        // TODO: 2023-11-09 채팅방 생성 로직이 먼저
        ChattingRoomDomain chattingRoomDomain = stompFindRoomPort.findRoom(cmd.roomId());

        Long targetId = Objects.equals(userId, chattingRoomDomain.buyerId()) ? chattingRoomDomain.sellerId() : chattingRoomDomain.buyerId();
        log.info("상대방 아이디: {}", targetId);

        ApiData<UserFindInfoRes> userRes = stompFindUserPort.findUser(cmd.token());
        if (userRes.status() != HttpStatus.OK.value()) {
            throw new ChatServiceException(ErrorCode.USER_NOT_FOUND);
        }

        log.info("내 정보 res: {}", userRes.data());

        ApiData<UserFindInfoRes> targetRes = stompFindUserPort.findUser(cmd.token(), targetId);
        if (targetRes.status() != HttpStatus.OK.value()) {
            throw new ChatServiceException(ErrorCode.USER_NOT_FOUND);
        }

        log.info("상대 정보 res: {}", targetRes.data());

//        Date currentTime = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//        String formattedTime = sdf.format(currentTime);

        // TODO: 2023-11-14 채팅방 안에서 읽음 구현시 이부분 수정 필요
        StompChatDomain stompChatDomain = StompChatDomain.of(targetRes.data().nickname(), cmd.content(), LocalDateTime.now());

        sendingOperations.convertAndSend("/sub/room/" + cmd.roomId().toString(), stompChatDomain);

        if (Objects.equals(stompFindConnPort.findConn(targetId), cmd.roomId().toString())) {
            chatCreatePort.createChat(cmd.content(), userId, 0L, cmd.roomId());
        }
        else{
            chatCreatePort.createChat(cmd.content(), userId, 1L, cmd.roomId());
        }

        // 채팅방 밖 send code
        BookRes bookRes = chatFindBookPort.findBook(cmd.token(), chattingRoomDomain.buId()).data();
        Long unreadCount = chatFindUnreadCountPort.unreadCount(targetId, cmd.roomId());

        ChattingRoomInfoDomain stompRoomDomain = ChattingRoomInfoDomain.of(cmd.roomId(), chattingRoomDomain.buId(), bookRes.title(), targetId, targetRes.data().nickname(), cmd.content(), LocalDateTime.now(), unreadCount);

        sendingOperations.convertAndSend("/sub/" + targetId.toString(), stompRoomDomain);
    }
}
