package org.bookulove.user.exception;

import org.bookulove.common.error.ErrorCode;
import org.bookulove.common.error.exception.ServiceRuntimeException;

public class UserServiceException extends ServiceRuntimeException {

    public UserServiceException(ErrorCode errorCode){
        super(errorCode);
    }

    public UserServiceException(ErrorCode errorCode, String msg){
        super(errorCode, msg);
    }
}
