package org.bookyoulove.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Global
    UNEXPECTED_ERROR(500, "G001", "Unexpected error!"),
    BAD_REQUEST(400, "G002", "Bad request!"),

    // Book
    IO_ERROR(500, "B001", "IO errpr!"),
    JSON_PARSE_ERROR(500, "B002", "Json parse errpr!"),
    EXTERNAL_API_ERROR(500, "B003", "External API error!"),
    LIBRARY_NOT_FOUND(500, "B004", "Library not found error!"),
    BOOK_NOT_FOUND(500, "B005", "book not found error!")


    ;

    private final int status;
    private final String code;
    private final String message;
}
