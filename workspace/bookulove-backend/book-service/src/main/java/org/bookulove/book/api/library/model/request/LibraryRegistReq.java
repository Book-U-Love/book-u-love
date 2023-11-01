package org.bookulove.book.api.library.model.request;

import jakarta.validation.constraints.NotBlank;

public record LibraryRegistReq (

    @NotBlank(message = "도서관 이름이 입력되지 않았습니다.")
    String name,

    String description,

    @NotBlank(message = "도서관 위도가 입력되지 않았습니다.")
    double lat,

    @NotBlank(message = "도서관 경도가 입력되지 않았습니다.")
    double lng

) {

}

