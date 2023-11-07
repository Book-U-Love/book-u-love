package org.bookyoulove.chatting.adapter.in.web.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record StompCreateReq(

        @NotNull(message = "채팅방 아이디는 필수입니다.")
        Long roomId,

        @NotEmpty(message = "채팅 내용은 필수입니다.")
        String content

) {
}
