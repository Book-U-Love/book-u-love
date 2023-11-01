package org.bookulove.apigateway.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {

    @Data
    public static class Config {
        private String message;
        private boolean showPreLogger;
        private boolean showPostLogger;
    }

    public GlobalFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            log.info("Global Filter Message: {}", config.getMessage());

            // Global pre Filter
            if (config.isShowPreLogger()) {
                log.info("Global Filter Start: request uri -> {}", request.getLocalAddress());
            }

            // Global Post Filter
            //Mono는 webflux에서 단일값 전송할때 Mono값으로 전송
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if (config.isShowPostLogger()) {
                    log.info("Global Filter End: response code -> {}", response.getStatusCode());
                }
            }));
        };
    }


}