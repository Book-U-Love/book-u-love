//package org.bookyoulove.auth.error;
//
//public record ApiError(
//
//        int status,
//
//        String code,
//
//        String message
//) {
//
////    public static ApiError error(int status, String code, String message){
////        return new ApiError(status, code, message);
////    }
////
//    public static ApiError error(ErrorCode errorCode){
//        return new ApiError(errorCode.getStatus(), errorCode.getCode(), errorCode.getMessage());
//    }
//
//    public static ApiError error(ErrorCode errorCode, String message){
//        return new ApiError(errorCode.getStatus(), errorCode.getCode(), message);
//    }
//}
