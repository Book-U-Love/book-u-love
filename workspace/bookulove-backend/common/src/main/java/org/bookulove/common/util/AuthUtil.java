package org.bookulove.common.util;

import org.bookulove.common.error.ErrorCode;
import org.bookulove.common.error.exception.InvalidValueException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class AuthUtil {

    public Long getUserIdByHeader() {

        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        try {
            return Long.parseLong(attributes.getRequest().getHeader("userId"));
        } catch (NullPointerException | NumberFormatException e) {
            throw new InvalidValueException(ErrorCode.UNAUTHORIZATION_ERROR);
        }
    }
}
