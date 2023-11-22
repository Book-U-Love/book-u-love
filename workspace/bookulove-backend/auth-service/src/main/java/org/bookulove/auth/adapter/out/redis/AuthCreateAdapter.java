package org.bookulove.auth.adapter.out.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.auth.adapter.out.redis.repository.JwtRepository;
import org.bookulove.auth.application.port.out.AuthCreatePort;
import org.bookulove.common.annotation.Adapter;

@Slf4j
@Adapter
@RequiredArgsConstructor
public class AuthCreateAdapter implements AuthCreatePort {

    private final JwtRepository jwtRepository;

    @Override
    public void createAuth(Long id, String refreshToken) {
        jwtRepository.saveAuth(id, refreshToken);
    }
}
