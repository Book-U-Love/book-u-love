package org.bookulove.auth.adapter.in.web.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SmsDeleteReq(

        @NotBlank(message = "핸드폰 번호는 필수값입니다.")
        String phoneNumber,

        @NotBlank(message = "인증번호는 필수값입니다.")
        String authCode

) {
}
