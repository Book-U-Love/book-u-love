package org.bookulove.api.controller.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserCreateReq(

        @NotNull(message = "아이디는 필수입니다.")
        @Size(max = 16, message = "아이디는 16자 이하만 가능합니다.")
        String id,

        @NotNull(message = "비밀번호는 필수입니다.")
        String password
) {
}
