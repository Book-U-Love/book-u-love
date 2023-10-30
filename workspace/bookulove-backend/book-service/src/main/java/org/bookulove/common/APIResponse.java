//package org.bookulove.common;
//
//import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.ToString;
//import org.springframework.web.ErrorResponse;
//
//@Getter
//@ToString
//@EqualsAndHashCode(callSuper = true)
//public class APIResponse<T> extends ErrorResponse {
//    private final T data;
//
//    private APIResponse(T data){
//        super(ErrorCode.OK.getCode(), ErrorCode.OK.getMessage());
//        this.data = data;
//    }
//
//    public static <T> APIResponse<T> of(T data){
//        return new APIResponse(data);
//    }
//}
