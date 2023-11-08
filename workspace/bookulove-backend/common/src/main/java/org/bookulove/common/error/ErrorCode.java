package org.bookulove.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Global
    UNEXPECTED_ERROR(500, "G001", "Unexpected error!"),
    BAD_REQUEST(400, "G002", "Bad request!"),

    // Jpa
    NON_TRANSACTION_ERROR(400, "J001", "Non transaction error!"),

    // Token
    INVALID_TOKEN_ERROR(400, "T001", "Invalid token error!"),
    LOGOUT_TOKEN(401, "T002", "Required login!"),
    UNAUTHORIZATION_ERROR(401, "T003", "Unauthorization error!"),
    INVALID_REFRESH_ERROR(401, "T003", "Invalid refresh token error!"),

    // Sms
    AUTHCODE_NOT_MATCH(400, "S001", "Authcode not match error!"),

    // Book
    IO_ERROR(500, "B001", "IO errpr!"),
    JSON_PARSE_ERROR(500, "B002", "Json parse errpr!"),
    EXTERNAL_API_ERROR(500, "B003", "External API error!"),
    LIBRARY_NOT_FOUND(500, "B004", "Library not found error!"),
    BOOK_NOT_FOUND(500, "B005", "book not found error!"),

    // Library
    LIBRARY_CREATE_ERROR(400, "L001", "Library create error!"),

    // User
    USER_NOT_FOUND(400, "U001", "User not found error!"),
    PASSWORD_NOT_MATCH(400, "U002", "Password not matched error!"),

    ;

    private final int status;
    private final String code;
    private final String message;
}