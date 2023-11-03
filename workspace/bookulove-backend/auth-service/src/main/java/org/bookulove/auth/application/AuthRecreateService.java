package org.bookulove.auth.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.auth.application.port.in.AuthRecreateUseCase;
import org.bookulove.auth.application.port.out.AuthFindTokenPort;
import org.bookulove.auth.global.exception.AuthServiceException;
import org.bookulove.auth.global.jwt.JwtUtil;
import org.bookulove.common.annotation.UseCase;
import org.bookulove.common.error.ErrorCode;
import org.bookulove.common.util.AuthUtil;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthRecreateService implements AuthRecreateUseCase {

    private final JwtUtil jwtUtil;
    private final AuthUtil authUtil;
    private final AuthFindTokenPort authFindTokenPort;

    @Override
    public String recreateAuth() {
        Long userId = authUtil.getUserIdByHeader();

        String refreshToken = authFindTokenPort.findToken(userId);
//        log.info("리프레시 토큰: {}", refreshToken);

        if(refreshToken == null){
            throw new AuthServiceException(ErrorCode.INVALID_REFRESH_ERROR);
        }

        String accessToken = jwtUtil.generateAccessToken(userId);
        log.info("어세스 토큰: {}", accessToken);

        return accessToken;
    }
}
