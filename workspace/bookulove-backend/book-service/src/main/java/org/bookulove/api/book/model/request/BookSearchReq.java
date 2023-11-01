package org.bookulove.api.book.model.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BookSearchReq (

        @NotBlank(message = "ISBN이 입력되지 않았습니다.")
        @Size(max = 13, min = 13, message = "유효한 13자리 ISBN13을 입력해주세요.")
        String isbn,

        @Max(value = 4, message = "알맞은 도서 상태를 선택해주세요")
        @Min(value = 0, message = "알맞은 도서 상태를 선택해주세요")
        int condition,

        boolean allowSale,

        boolean allowBorrow,

        String details

) {
//        public BookSearchReq to
}
