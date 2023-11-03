package org.bookulove.auth.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.auth.application.port.in.AuthCreateUseCase;
import org.bookulove.auth.application.port.out.AuthFindUserPort;
import org.bookulove.auth.application.port.out.AuthSavePort;
import org.bookulove.auth.domain.AuthCreateDomain;
import org.bookulove.auth.application.port.in.dto.command.AuthCreateCmd;
import org.bookulove.auth.domain.AuthFindUserDomain;
import org.bookulove.auth.global.exception.AuthServiceException;
import org.bookulove.auth.global.jwt.JwtUtil;
import org.bookulove.common.annotation.UseCase;
import org.bookulove.common.error.ErrorCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthCreateService implements AuthCreateUseCase {

    private final JwtUtil jwtUtil;
    private final AuthFindUserPort authFindPort;
    private final AuthSavePort authSavePort;
    private final BCryptPasswordEncoder encoder;

    @Override
    public AuthCreateDomain createAuth(AuthCreateCmd cmd) {
        log.info("권한 생성 cmd: {}", cmd);

        AuthFindUserDomain authFindDomain = authFindPort.findUser(cmd.id());
        log.info("유저 조회 domain: {}", authFindDomain);

        if(!encoder.matches(cmd.password(), authFindDomain.password())){
            throw new AuthServiceException(ErrorCode.PASSWORD_NOT_MATCH);
        }

        String accessToken = jwtUtil.generateAccessToken(authFindDomain.id());
        String refreshToken = jwtUtil.generateRefreshToken(authFindDomain.id());
        log.info("accessToken: {} \n refreshToken: {}", accessToken, refreshToken);

        authSavePort.saveAuth(authFindDomain.id(), refreshToken);

        return AuthCreateDomain.of(accessToken, refreshToken);
    }
}