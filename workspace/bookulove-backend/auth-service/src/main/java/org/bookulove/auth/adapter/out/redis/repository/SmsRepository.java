package org.bookulove.auth.adapter.out.redis.repository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Slf4j
@Repository
public class SmsRepository {

    private final Duration expireTime;

    private final StringRedisTemplate smsRedisTemplate;

    public SmsRepository(StringRedisTemplate redisTemplate){
        this.expireTime = Duration.ofMinutes(3);
        this.smsRedisTemplate = redisTemplate;
    }

    public void saveSms(String phoneNumber, String authCode){
        smsRedisTemplate.opsForValue().set(phoneNumber, authCode, expireTime);
    }

    public String getsms(String phoneNumber){
        String authCode = smsRedisTemplate.opsForValue().get(phoneNumber);
        log.info("인증코드: {}", authCode);
        return authCode;
    }

    public void deleteSms(String phoneNumber){
        smsRedisTemplate.delete(phoneNumber);
    }

}
