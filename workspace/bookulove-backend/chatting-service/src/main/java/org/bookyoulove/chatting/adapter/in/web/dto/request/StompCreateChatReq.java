package org.bookyoulove.chatting.adapter.in.web.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record StompCreateChatReq(

        @NotEmpty(message = "채팅 내용은 필수입니다.")
        String content
) {
}
