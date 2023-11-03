package org.bookulove.common.api.response;

import org.bookulove.common.error.ErrorCode;
import org.springframework.http.HttpStatus;

public record ApiData<T>(

        int status,

        String code,

        T data

) {
    public static <T> ApiData<T> ok(T data){
        return new ApiData<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
    }

//    public static <T> ApiData<T> of(int status, String code, T data){
//        return new ApiData<>(status, code, data);
//    }

    public static ApiData<String> error(ErrorCode errorCode, String message){
        return new ApiData<>(errorCode.getStatus(), errorCode.getCode(), message);
    }

    public static ApiData<String> error(ErrorCode errorCode){
        return new ApiData<>(errorCode.getStatus(), errorCode.getCode(), errorCode.getMessage());
    }

    public static ApiData<String> error(int status, String code, String message){
        return new ApiData<>(status, code, message);
    }

    public static <T> ApiData<T> of(int status, T data){
        return new ApiData<>(status, null, data);
    }
}
