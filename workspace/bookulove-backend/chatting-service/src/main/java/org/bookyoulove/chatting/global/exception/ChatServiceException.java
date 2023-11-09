package org.bookyoulove.chatting.global.exception;

import org.bookulove.common.error.ErrorCode;
import org.bookulove.common.error.exception.ServiceRuntimeException;

public class ChatServiceException extends ServiceRuntimeException {
    public ChatServiceException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public ChatServiceException(ErrorCode errorCode) {
        super(errorCode);
    }
}
