package org.bookyoulove.auth.error;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.bookyoulove.auth.ApiData;
import org.bookyoulove.auth.error.exception.ServiceRuntimeException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiData<String> handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ApiData.error(ErrorCode.UNEXPECTED_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(value = {ServiceRuntimeException.class})
    public ApiData<String> handleException(ServiceRuntimeException exception){
        log.error(exception.getMessage(), exception);
        return ApiData.error(exception.getErrorCode(), exception.getMessage());
    }


    @ResponseBody
    @ExceptionHandler(value = {ValidationException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiData<String> handleException(ValidationException validationException) {
        if (validationException instanceof ConstraintViolationException) {
            String violations = extractViolationsFromException((ConstraintViolationException) validationException);
            log.error(violations, validationException);
            return ApiData.error(ErrorCode.BAD_REQUEST, violations);
        } else {
            String exceptionMessage = validationException.getMessage();
            log.error(exceptionMessage, validationException);
            return ApiData.error(ErrorCode.BAD_REQUEST, exceptionMessage);
        }
    }

    private String extractViolationsFromException(ConstraintViolationException validationException) {
        return validationException.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("--"));
    }

    @ResponseBody
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiData<String> handleException(MethodArgumentNotValidException methodArgumentNotValidException){
        String argNotValid = extractArgsFromException(methodArgumentNotValidException);
        log.error(argNotValid, methodArgumentNotValidException);
        return ApiData.error(ErrorCode.BAD_REQUEST, argNotValid);
    }

    private String extractArgsFromException(MethodArgumentNotValidException methodArgumentNotValidException) {
        return methodArgumentNotValidException
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(
                        DefaultMessageSourceResolvable::getDefaultMessage
                )
                .toList()
                .toString();
    }
}

