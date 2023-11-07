package org.bookyoulove.chatting.adapter.in.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookyoulove.chatting.adapter.in.web.dto.request.StompCreateReq;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StompController {

    private final SimpMessageSendingOperations sendingOperations;

    @MessageMapping("/chatting-room/{id}")
    public void greetStomp(@DestinationVariable Long id, StompCreateReq req){
        log.info("채팅방 번호: {} \n 메시지 발행 req: {}", id, req);
        sendingOperations.convertAndSend("sub/chatting-room/" + id.toString(), req);
    }
}
