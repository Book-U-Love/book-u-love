package org.bookyoulove.chatting.application.port.in.dto.request;

import jakarta.validation.constraints.NotNull;
import org.bookyoulove.chatting.adapter.in.web.dto.request.ChatCreateRoomReq;

public record ChatCreateRoomCmd(

        Long buId,

        Long sellerId

) {
    public static ChatCreateRoomCmd of(ChatCreateRoomReq req) {
        return new ChatCreateRoomCmd(req.buId(), req.sellerId());
    }
}
