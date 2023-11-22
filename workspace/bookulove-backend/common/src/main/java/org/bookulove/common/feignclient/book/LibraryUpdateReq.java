package org.bookulove.common.feignclient.book;

import jakarta.validation.constraints.NotBlank;

public record LibraryUpdateReq(

        String name,

        double lat,

        double lng
) {

    public static LibraryUpdateReq of(String name, double lat, double lng) {
        return new LibraryUpdateReq(name, lat, lng);
    }
}
