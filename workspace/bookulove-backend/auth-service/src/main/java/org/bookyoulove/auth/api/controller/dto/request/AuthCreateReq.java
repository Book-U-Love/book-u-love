package org.bookyoulove.auth.api.controller.dto.request;

import jakarta.validation.constraints.NotNull;

public record AuthCreateReq(

        @NotNull(message = "아이디를 입력해주세요.")
        String id,

        @NotNull(message = "패스워드를 입력해주세요.")
        String password

) {
}
