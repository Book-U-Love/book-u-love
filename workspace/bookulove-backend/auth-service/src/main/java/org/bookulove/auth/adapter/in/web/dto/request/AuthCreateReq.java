package org.bookulove.auth.adapter.in.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthCreateReq(

        @Size(max = 32, message = "아이디 길이는 16글자 이하입니다.")
        @NotBlank(message = "아이디는 필수값입니다.")
        String id,

        @NotBlank(message = "패스워드는 필수값입니다.")
        String password
) {
}
