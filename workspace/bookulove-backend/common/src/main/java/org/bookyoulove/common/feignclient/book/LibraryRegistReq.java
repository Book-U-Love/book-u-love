package org.bookyoulove.common.feignclient.book;

import jakarta.validation.constraints.NotBlank;

public record LibraryRegistReq (

    @NotBlank(message = "도서관 이름이 입력되지 않았습니다.")
    String name,

    @NotBlank(message = "도서관 위도가 입력되지 않았습니다.")
    double lat,

    @NotBlank(message = "도서관 경도가 입력되지 않았습니다.")
    double lng

) {

    public static LibraryRegistReq of(String name, double lat, double lng){
        return new LibraryRegistReq(name, lat, lng);
    }
}

