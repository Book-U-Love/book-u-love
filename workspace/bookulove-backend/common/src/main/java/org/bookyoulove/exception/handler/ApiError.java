package org.bookyoulove.exception.handler;

public record ApiError(
        String code,
        String message
) {

    public static ApiError error(String code, String message){
        return new ApiError(code, message);
    }
}
