package org.bookulove.auth.adapter.in.web.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SmsCreateReq(

        @NotBlank(message = "핸드폰 번호는 필수값입니다.")
        String phoneNumber
) {
}
