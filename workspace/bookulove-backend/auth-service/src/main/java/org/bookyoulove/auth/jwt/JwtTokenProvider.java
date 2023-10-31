package org.bookyoulove.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    private final Key key;
    private final Date accessExpireTime;

    private final Date refreshExpireTime;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey,
                            @Value("${jwt.access-expire-time}") long accessExpireTime,
                            @Value("${jwt.refresh-expire-time}") long refreshExpireTime){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);

        Date now = new Date();
        this.accessExpireTime = new Date(now.getTime() + accessExpireTime);
        this.refreshExpireTime = new Date(now.getTime() + refreshExpireTime);

    }

    public String generate(Long userId){
        return Jwts.builder()
                .setClaims(createClaims(userId))
                .setExpiration(accessExpireTime)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims createClaims(Long userId) {
        Claims claims = Jwts.claims();
        claims.put("id", userId);

        return claims;
    }

}
