package org.bookulove.auth.global.jwt;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.error.ErrorCode;
import org.bookulove.common.error.exception.InvalidValueException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    private final Key key;

    @Value("${jwt.access-expire-time}")
    private Long accessExpireTime;

    @Value("${jwt.refresh-expire-time}")
    private Long refreshExpireTime;

    public JwtUtil(@Value("${jwt.secret}") String secretKey){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateAccessToken(Long userId){
        Date now = new Date();
        return Jwts.builder()
                .setClaims(createClaims(userId))
                .setExpiration(new Date(now.getTime() + accessExpireTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(Long userId){
        Date now = new Date();
        return Jwts.builder()
                .setClaims(createClaims(userId))
                .setExpiration(new Date(now.getTime() + refreshExpireTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims createClaims(Long userId) {
        Claims claims = Jwts.claims();
        claims.put("id", userId);

        return claims;
    }

    public Long extractId(String token) {return extractAllClaims(token).get("id", Long.class);}

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        }catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }

        throw new InvalidValueException(ErrorCode.INVALID_TOKEN_ERROR);
    }
}
