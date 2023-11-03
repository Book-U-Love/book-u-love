package org.bookulove.auth.adapter.out.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.auth.adapter.out.redis.repository.JwtRepository;
import org.bookulove.auth.application.port.out.AuthFindTokenPort;
import org.bookulove.common.annotation.Adapter;

@Slf4j
@Adapter
@RequiredArgsConstructor
public class AuthFindTokenAdapter implements AuthFindTokenPort {

    private final JwtRepository jwtRepository;

    @Override
    public String findToken(Long userId) {
        return jwtRepository.getAuth(userId);
    }
}
