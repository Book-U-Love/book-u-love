package org.bookyoulove.auth.error.exception;


import org.bookyoulove.auth.error.ErrorCode;

public class InvalidValueException extends ServiceRuntimeException {

    public InvalidValueException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public InvalidValueException(ErrorCode errorCode) {
        super(errorCode);
    }
}
