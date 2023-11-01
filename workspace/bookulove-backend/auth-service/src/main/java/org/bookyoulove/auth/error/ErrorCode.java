package org.bookyoulove.auth.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    UNEXPECTED_ERROR(500, "G001", "Unexpected error!"),
    BAD_REQUEST(400, "G002", "Bad request!"),


    //Token
    INVALID_TOKEN(401, "T001", "올바르지 않은 토큰입니다!"),

    ;

    private final int status;
    private final String code;
    private final String message;
}
