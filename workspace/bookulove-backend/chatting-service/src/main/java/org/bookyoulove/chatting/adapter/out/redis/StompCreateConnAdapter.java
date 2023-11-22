package org.bookyoulove.chatting.adapter.out.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.Adapter;
import org.bookyoulove.chatting.adapter.out.redis.repository.ConnRepository;
import org.bookyoulove.chatting.application.port.out.StompCreateConnPort;

@Slf4j
@Adapter
@RequiredArgsConstructor
public class StompCreateConnAdapter implements StompCreateConnPort {

    private final ConnRepository roomRepository;

    @Override
    public void createConn(Long userId, String roomId) {
        roomRepository.saveConn(userId, roomId);
    }
}
