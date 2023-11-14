package org.bookyoulove.chatting.global.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookyoulove.chatting.global.util.JwtUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("요청 url: {}", request.getRequestURL());

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authHeader: {}", token);

        if (token == null || !(token.startsWith("Bearer "))) {
            log.error("authentication is null");
            filterChain.doFilter(request, response);
            return;
        }

        token = token.replace("Bearer", "");
        log.info("token: {}", token);

        Long userId = jwtUtil.extractId(token);

        // TODO: 2023-11-13 redis blacklist

        Authentication authentication = getAuthentication(userId, token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    private Authentication getAuthentication(Long userId, String token){
        SecurityDto securityDto = SecurityDto.of(userId, token);
        log.info("securityDto: {}", securityDto);

        return new UsernamePasswordAuthenticationToken(securityDto, "",
                List.of(new SimpleGrantedAuthority("member")));
    }
}
