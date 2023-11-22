package org.bookulove.auth.global.exception;

import org.bookulove.common.error.ErrorCode;
import org.bookulove.common.error.exception.ServiceRuntimeException;

public class AuthServiceException extends ServiceRuntimeException {
    public AuthServiceException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public AuthServiceException(ErrorCode errorCode) {
        super(errorCode);
    }
}
