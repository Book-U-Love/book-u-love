package org.bookulove.api.book.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BookSearchReq (

        @NotBlank(message = "ISBN이 입력되지 않았습니다.")
        @Size(max = 13, min = 13, message = "유효한 13자리 ISBN13을 입력해주세요.")
        String isbn

) {

}
