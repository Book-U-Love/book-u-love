package org.bookyoulove.chatting.adapter.out.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.Adapter;
import org.bookyoulove.chatting.adapter.out.redis.repository.ConnRepository;
import org.bookyoulove.chatting.application.port.out.StompFindConnPort;

@Slf4j
@Adapter
@RequiredArgsConstructor
public class StompFindConnAdapter implements StompFindConnPort {

    private final ConnRepository connRepository;

    @Override
    public String findConn(Long targetId) {
        return connRepository.getConn(targetId);
    }
}
