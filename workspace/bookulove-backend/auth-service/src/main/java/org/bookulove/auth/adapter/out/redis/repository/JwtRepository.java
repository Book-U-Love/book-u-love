package org.bookulove.auth.adapter.out.redis.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.auth.global.exception.AuthServiceException;
import org.bookulove.common.error.ErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Slf4j
@Repository
public class JwtRepository {
    private static final String BLACKLIST_PREFIX = "BLACKLISTID:";

    private final StringRedisTemplate jwtRedisTemplate;

    private final Duration expireTime;


    public JwtRepository(@Value("${jwt.refresh-expire-time}") Long refreshExpireTime,
                         StringRedisTemplate redisTemplate) {
        this.expireTime = Duration.ofMillis(refreshExpireTime);
        this.jwtRedisTemplate = redisTemplate;
    }


    public void saveAuth(Long id, String refreshToken) {
        jwtRedisTemplate.opsForValue().set(id.toString(), refreshToken, expireTime);
        jwtRedisTemplate.delete(BLACKLIST_PREFIX + id.toString());
    }

    public String getAuth(Long id) {
        String refreshToken = jwtRedisTemplate.opsForValue().get(id.toString());
        log.info("리프레시 토큰: {}", refreshToken);
        return refreshToken;
    }

    public void deleteAuth(Long id) {
        jwtRedisTemplate.delete(id.toString());
        jwtRedisTemplate.opsForValue().set(BLACKLIST_PREFIX + id.toString(), id.toString(), expireTime);
    }


}
