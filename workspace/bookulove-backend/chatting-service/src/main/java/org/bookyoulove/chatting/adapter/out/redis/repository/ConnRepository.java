package org.bookyoulove.chatting.adapter.out.redis.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ConnRepository {
    private static final String STOMP_PREFIX = "STOMPID: ";

    private final StringRedisTemplate roomRedisTemplate;

    public void saveConn(Long userId, String roomId) {
        roomRedisTemplate.opsForValue().set(STOMP_PREFIX + userId.toString(), roomId);
    }

    public String getConn(Long userId) {
        String roomId = roomRedisTemplate.opsForValue().get(STOMP_PREFIX + userId.toString());
        log.info("접속중인 방: {}", roomId);
        return roomId;
    }

    public void deleteConn(Long userId) {
        roomRedisTemplate.delete(STOMP_PREFIX + userId.toString());
    }


}
