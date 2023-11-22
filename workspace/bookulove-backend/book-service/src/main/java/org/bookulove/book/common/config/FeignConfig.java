package org.bookulove.book.common.config;

import feign.*;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;

import java.util.*;

@Configuration
@EnableFeignClients("org.bookulove")
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL; // log레벨 설정
    }

    @Bean
    public Client feignClient() {
        return new Client.Default(null, null);
    }

    @Bean
    public FeignFormatterRegistrar dateTimeFormatterRegistrar() {   // LocalDate, LocalDateTime, LocalTime 등을 위한 설정
        return registry -> {
            DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
            registrar.setUseIsoFormat(true);
            registrar.registerFormatters(registry);
        };
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header("Accept", "application/json");
//            requestTemplate.header(HttpHeaders.AUTHORIZATION, RequestUtil.getToken());
//            requestTemplate.header("user-id", RequestUtil.getUserId().toString());
        };
    }

}
