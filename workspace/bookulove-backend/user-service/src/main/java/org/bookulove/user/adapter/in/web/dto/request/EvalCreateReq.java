package org.bookulove.user.adapter.in.web.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public record EvalCreateReq(

        @Min(value = 1, message = "리뷰이 아이디는 1이상입니다.")
        @NotNull(message = "리뷰이 아이디는 필수값입니다.")
        Long revieweeId,

        @Range(min = 0, max = 5, message = "평점은 0에서 5 사이입니다.")
        @NotNull(message = "평점은 필수값입니다.")
        int grade,

        @NotBlank(message = "평가 내용은 필수값입니다.")
        String content

) {
}
