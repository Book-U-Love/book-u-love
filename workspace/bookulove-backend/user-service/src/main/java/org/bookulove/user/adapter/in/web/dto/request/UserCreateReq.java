package org.bookulove.user.adapter.in.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserCreateReq(

        @Size(max = 32, message = "아이디 길이는 32글자 이하입니다.")
        @NotBlank(message = "아이디는 필수값입니다.")
        String id,

        @NotBlank(message = "패스워드는 필수값입니다.")
        String password,

        @NotBlank(message = "핸드폰 번호는 핋수값입니다.")
        String phoneNumber,

        @Size(max = 16, message = "닉네임 길이는 16글자 이하입니다.")
        @NotBlank(message = "닉네임은 필수값입니다.")
        String nickname,

        @Size(max = 16, message = "도서관 이름은 16글자 이하입니다.")
        @NotBlank(message = "도서관 이름은 필수입니다.")
        String libraryName,

        @NotNull(message = "도서관 위도는 필수값입니다.")
        double lat,

        @NotNull(message = "도서관 경도는 필수값입니다.")
        double lng

) {
}
