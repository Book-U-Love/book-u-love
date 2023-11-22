package org.bookyoulove.chatting.application.port.in.dto.request;

import org.bookyoulove.chatting.adapter.in.web.dto.request.StompCreateChatReq;

public record StompCreateChatCmd(

        String token,

        Long roomId,

        String content

) {

    public static StompCreateChatCmd of(String token, Long roomId, StompCreateChatReq req){
        return new StompCreateChatCmd(token, roomId, req.content());
    }
}
