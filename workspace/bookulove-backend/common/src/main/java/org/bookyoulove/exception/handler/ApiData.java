package org.bookyoulove.exception.handler;

import org.springframework.http.HttpStatus;

public record ApiData<T>(

        int status,

        String code,

        T data

) {
    public static <T> ApiData<T> ok(T data){
        return new ApiData<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
    }
}
