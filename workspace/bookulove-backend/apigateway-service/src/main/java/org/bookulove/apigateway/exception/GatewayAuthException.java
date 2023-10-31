package org.bookulove.apigateway.exception;

import org.bookyoulove.common.error.ErrorCode;
import org.bookyoulove.common.error.exception.ServiceRuntimeException;

public class GatewayAuthException extends ServiceRuntimeException {

    public GatewayAuthException(ErrorCode errorCode){
        super(errorCode);
    }

    public GatewayAuthException(ErrorCode errorCode, String msg){
        super(errorCode, msg);
    }
}
