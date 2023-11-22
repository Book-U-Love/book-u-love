package org.bookulove.apigateway.global.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

// TODO: 2023-11-08 redis repository로 이름 고민해보기 

@Slf4j
@Repository
@RequiredArgsConstructor
public class RedisUtil {

    private static final String BLACKLIST_PREFIX = "BLACKLISTID:";

    private final StringRedisTemplate jwtRedisTemplate;

    public String getAuth(Long id) {
        String refreshToken = jwtRedisTemplate.opsForValue().get(BLACKLIST_PREFIX + id.toString());
        log.info("리프레시 토큰: {}", refreshToken);
        return refreshToken;
    }
}
