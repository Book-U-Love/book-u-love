package org.bookulove.book.exception;

import org.bookulove.common.error.ErrorCode;
import org.bookulove.common.error.exception.ServiceRuntimeException;

public class BookServiceException extends ServiceRuntimeException {

    public BookServiceException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public BookServiceException(ErrorCode errorCode) {
        super(errorCode);
    }

}
