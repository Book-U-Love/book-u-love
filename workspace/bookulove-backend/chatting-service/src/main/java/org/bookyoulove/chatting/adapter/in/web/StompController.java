package org.bookyoulove.chatting.adapter.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookyoulove.chatting.adapter.in.web.dto.request.StompCreateChatReq;
import org.bookyoulove.chatting.adapter.in.web.dto.request.StompCreateRoomReq;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StompController {

    private final SimpMessageSendingOperations sendingOperations;

    @MessageMapping
    public void craeteRoomStomp(@Header("Authorization") String token, StompCreateRoomReq req){

    }

    @MessageMapping("/{roomId}")
    public void createChatStomp(@Header("Authorization") String token, @DestinationVariable Long roomId, StompCreateChatReq req){
        log.info("채팅방 번호: {} \n 메시지 발행 req: {}", roomId, req);
        sendingOperations.convertAndSend("sub/" + roomId.toString(), req);
    }


}
