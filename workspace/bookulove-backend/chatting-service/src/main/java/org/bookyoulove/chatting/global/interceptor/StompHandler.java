package org.bookyoulove.chatting.global.interceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookyoulove.chatting.application.port.out.StompCreateConnPort;
import org.bookyoulove.chatting.global.util.JwtUtil;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

import org.springframework.stereotype.Component;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
@Component
@RequiredArgsConstructor
public class StompHandler implements ChannelInterceptor {

    private final JwtUtil jwtUtil;
    private final StompCreateConnPort stompCreateConnPort;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        log.info("accessCommand: {}", accessor);

        switch (accessor.getCommand()) {
            case CONNECT -> {
                Long userId = jwtUtil.extractId(getAccessToken(accessor));
            }
            case SUBSCRIBE-> {
                String simpDestination = message.getHeaders().get("simpDestination").toString();
                log.info("simpDestination: {}", simpDestination);

                String roomId = getRoomId(simpDestination);
                log.info("roomId: {}", roomId);

                // TODO: 2023-11-08 kotlin에서 jwt 잘 넘어오는지 확인후 검증작업 추가

                stompCreateConnPort.createConn(1L, roomId);
            }
            case SEND -> {

            }
        }
        return message;
    }

    private String getAccessToken(StompHeaderAccessor accessor) {
        String accessToken = accessor.getFirstNativeHeader("Authorization");
        log.info("AccessToken: {}", accessToken);
        return accessToken;
    }

    private String getRoomId(String simpDestination) {
        if (simpDestination.length() <= 5) {
            return "room";
        }
        else return simpDestination.substring(5);
    }


}
