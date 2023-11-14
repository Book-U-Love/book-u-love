package org.bookyoulove.chatting.adapter.out.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.Adapter;
import org.bookyoulove.chatting.adapter.out.redis.repository.ConnRepository;
import org.bookyoulove.chatting.application.port.out.ChatDeleteConnPort;

@Slf4j
@Adapter
@RequiredArgsConstructor
public class ChatDeleteConnAdapter implements ChatDeleteConnPort {

    private final ConnRepository connRepository;

    @Override
    public void deleteConn(Long userId) {
        connRepository.deleteConn(userId);
    }
}
