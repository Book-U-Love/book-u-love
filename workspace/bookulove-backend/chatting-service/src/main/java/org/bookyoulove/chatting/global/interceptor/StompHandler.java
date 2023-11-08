package org.bookyoulove.chatting.global.interceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookyoulove.chatting.adapter.in.web.dto.request.StompCreateReq;
import org.bookyoulove.chatting.global.util.JwtUtil;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
@Component
@RequiredArgsConstructor
public class StompHandler implements ChannelInterceptor {

    private final JwtUtil jwtUtil;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        log.info("StompAccessor: {}", accessor);

        handleMessage(accessor.getCommand(), accessor);
        return message;
    }

    private void handleMessage(StompCommand stompCommand, StompHeaderAccessor accessor) {
        switch (stompCommand) {
            case CONNECT -> {
                Long userId = jwtUtil.extractId(getAccessToken(accessor));
                log.info("userId: {}", userId);
            }
            case SUBSCRIBE, SEND -> {
//                Long userId = jwtUtil.extractId(getAccessToken(accessor));
//                log.info("userId: {}", userId);
            }
        }
    }

    private String getAccessToken(StompHeaderAccessor accessor) {
        String accessToken = accessor.getFirstNativeHeader("Authorization");
        log.info("AccessToken: {}", accessToken);
        return accessToken;
    }

}
