package org.bookyoulove.chatting.global.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

@Slf4j
@Configuration
@EnableWebSocketMessageBroker
public class StompConfig implements WebSocketMessageBrokerConfigurer {

//    private final StompHandler stompHandler; // jwt 인증

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomps")
                .setAllowedOrigins("*")
                .setHandshakeHandler(new MyHandshakeHandler());
//                .withSockJS(); // SocketJS 를 연결한다는 설정
    }

    // STOMP에서 사용하는 메시지 브로커 설정
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/sub");
        registry.setApplicationDestinationPrefixes("/pub");
    }

    // URL을 로깅하기 위한 커스텀 핸드셰이크 핸들러
    private static class MyHandshakeHandler extends DefaultHandshakeHandler {
        @Override
        protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
            Principal principal = super.determineUser(request, wsHandler, attributes);

            // 연결된 URL을 로그로 출력
            String connectionUrl = request.getURI().toString();
            log.info("WebSocket 연결 URL: {}", connectionUrl);

            return principal;
        }
    }

//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(stompHandler);
//    }
}
