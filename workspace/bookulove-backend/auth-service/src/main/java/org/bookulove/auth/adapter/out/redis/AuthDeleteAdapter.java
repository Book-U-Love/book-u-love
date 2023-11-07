package org.bookulove.auth.adapter.out.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.auth.adapter.out.redis.repository.JwtRepository;
import org.bookulove.auth.application.port.out.AuthDeletePort;
import org.bookulove.common.annotation.Adapter;

@Slf4j
@Adapter
@RequiredArgsConstructor
public class AuthDeleteAdapter implements AuthDeletePort {

    private final JwtRepository jwtRepository;

    @Override
    public void deleteAuth(Long userId) {
        jwtRepository.deleteAuth(userId);
    }
}
