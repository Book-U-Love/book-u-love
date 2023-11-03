package org.bookulove.user.adapter.in.web.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserUpdatePasswordReq(

        @NotBlank(message = "예전 패스워드는 필수값입니다.")
        String oldPassword,

        @NotBlank(message = "새로운 패스워드는 필수값입니다.")
        String newPassword
) {
}
