package org.bookulove.common.feignclient.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LibraryCreateReq(

        @NotNull(message = "도서관 아이디는 필수값입니다.")
        Long id,

        @Size(max = 16, message = "도서관 이름은 16글자 이하입니다.")
        @NotBlank(message = "도서관 이름은 필수입니다.")
        String libraryName,

        @NotNull(message = "도서관 위도는 필수값입니다.")
        double lat,

        @NotNull(message = "도서관 경도는 필수값입니다.")
        double lng

) {

    public static LibraryCreateReq of(Long id, String name, double lat, double lng){
        return new LibraryCreateReq(id, name, lat, lng);
    }
}

