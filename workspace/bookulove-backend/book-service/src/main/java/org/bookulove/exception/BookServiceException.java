package org.bookulove.exception;

import org.bookyoulove.common.error.ErrorCode;
import org.bookyoulove.common.error.exception.ServiceRuntimeException;

public class BookServiceException extends ServiceRuntimeException {

    public BookServiceException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public BookServiceException(ErrorCode errorCode) {
        super(errorCode);
    }

}
