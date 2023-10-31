package org.bookulove.user.adapter.in.web.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserCreateReq(

        @Size(max = 16, message = "아이디 길이는 16글자 이하입니다.")
        @NotNull(message = "아이디는 필수값입니다.")
        String id,

        @NotNull(message = "패스워드는 필수값입니다.")
        String password,

        @Size(max = 16, message = "닉네임 길이는 16글자 이하입니다.")
        @NotNull(message = "닉네임은 필수값입니다.")
        String nickname

) {
}
