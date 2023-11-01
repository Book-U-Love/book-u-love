package org.bookyoulove.common.error.exception;

import org.bookyoulove.common.error.ErrorCode;

public class InvalidValueException extends ServiceRuntimeException {

    public InvalidValueException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public InvalidValueException(ErrorCode errorCode) {
        super(errorCode);
    }
}
