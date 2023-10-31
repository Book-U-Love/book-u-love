//package org.bookyoulove.filter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {
//
//    @Value("${jwt.secret}")
//    private String secret;
//
//    public AuthorizationHeaderFilter(){
//        super(Config.class);
//    }
//
//    public static class Config{
//
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return (exchange, chain) -> {
//            ServerHttpRequest request = exchange.getRequest();
//        };
//    }
//
//
//
//}
