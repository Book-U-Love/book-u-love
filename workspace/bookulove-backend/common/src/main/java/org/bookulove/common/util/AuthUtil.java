package org.bookulove.common.util;

import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.error.ErrorCode;
import org.bookulove.common.error.exception.InvalidValueException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Component
public class AuthUtil {

    public Long getUserIdByHeader() {

        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        try {
            Long userId = Long.parseLong(attributes.getRequest().getHeader("userId"));
            log.info("현재접속유저 id: {}", userId);
            return userId;
        } catch (NullPointerException | NumberFormatException e) {
            throw new InvalidValueException(ErrorCode.UNAUTHORIZATION_ERROR);
        }
    }
}
