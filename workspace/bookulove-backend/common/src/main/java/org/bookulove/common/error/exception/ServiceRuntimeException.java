package org.bookulove.common.error.exception;

import lombok.Getter;
import org.bookulove.common.error.ErrorCode;

@Getter
public class ServiceRuntimeException extends RuntimeException {

    private String msg;
    private ErrorCode errorCode;

    public ServiceRuntimeException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ServiceRuntimeException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
