package org.bookulove.book.api.book.model.request;

import jakarta.validation.constraints.NotBlank;

import java.util.Optional;

public record   BookUpdateReq(

        @NotBlank(message = "BUID가 입력되지 않았습니다.")
        Long buid,

        Optional<String> condition,

        Optional<Boolean> allowSale,

        Optional<Boolean> allowBorrow,

        Optional<Boolean> isBorrow,

        Optional<Boolean> isRemoved,

        Optional<String> details

) {
}
