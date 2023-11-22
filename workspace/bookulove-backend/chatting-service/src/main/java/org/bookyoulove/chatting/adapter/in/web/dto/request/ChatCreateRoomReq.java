package org.bookyoulove.chatting.adapter.in.web.dto.request;

import jakarta.validation.constraints.NotNull;

public record ChatCreateRoomReq(

        @NotNull(message = "구매하려는 책 아이디는 필수값입니다.")
        Long buId,

        @NotNull(message = "판매자 아이디는 필수값입니다.")
        Long sellerId

) {
}
