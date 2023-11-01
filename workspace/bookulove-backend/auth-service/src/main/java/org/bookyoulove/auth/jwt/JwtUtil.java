package org.bookyoulove.auth.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.bookyoulove.auth.error.ErrorCode;
import org.bookyoulove.auth.error.exception.InvalidValueException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    private final Key key;
    private final Date accessExpireTime;

    private final Date refreshExpireTime;

    public JwtUtil(@Value("${jwt.secret}") String secretKey,
                   @Value("${jwt.access-expire-time}") long accessExpireTime,
                   @Value("${jwt.refresh-expire-time}") long refreshExpireTime){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);

        Date now = new Date();
        this.accessExpireTime = new Date(now.getTime() + accessExpireTime);
        this.refreshExpireTime = new Date(now.getTime() + refreshExpireTime);

    }

    public String generateAccessToken(Long userId){
        return Jwts.builder()
                .setClaims(createClaims(userId))
                .setExpiration(accessExpireTime)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(Long userId){
        return Jwts.builder()
                .setClaims(createClaims(userId))
                .setExpiration(refreshExpireTime)
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

        throw new InvalidValueException(ErrorCode.INVALID_TOKEN);
    }



}
