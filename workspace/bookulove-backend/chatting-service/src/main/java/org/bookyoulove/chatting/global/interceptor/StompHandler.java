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
                log.info("접속중인 회원 id: {}", userId);
            }
            case SUBSCRIBE-> {
                String simpDestination = message.getHeaders().get("simpDestination").toString();
                log.info("simpDestination: {}", simpDestination);

                String token = getAccessToken(accessor);
                log.info("subscribe token: {}", token);

                Long userId = jwtUtil.extractId(token);
                log.info("userId: {}", userId);

                String roomId = getPathId(simpDestination, userId);
                log.info("pathId: {}", roomId);

            }
            case SEND -> {
                log.info("sendhandling");
                Long userId = jwtUtil.extractId(getAccessToken(accessor));
                log.info("접속중인 회원 id: {}", userId);
            }
        }
        return message;
    }

    private String getAccessToken(StompHeaderAccessor accessor) {
        String accessToken = accessor.getFirstNativeHeader("Authorization");
        log.info("AccessToken: {}", accessToken);

        if(accessToken == null || !(accessToken.startsWith("Bearer "))){
            log.error("authentication is null");
            return null;
        }

        accessToken = accessToken.replace("Bearer", "");
        log.info("token: {}", accessToken);
        return accessToken;
    }

    private String getPathId(String simpDestination, Long userId) {
        if(simpDestination.contains("/sub/room/")){
            String roomId = simpDestination.substring(10);
            stompCreateConnPort.createConn(userId, roomId);
            return roomId;
        }
        else return simpDestination.substring(5);
    }

}
