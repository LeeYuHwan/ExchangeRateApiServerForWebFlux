package com.doha.practice.jwt;

import com.doha.practice.domain.UserInfo;
import com.doha.practice.properties.JwtConfigProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider implements InitializingBean {

    private final JwtConfigProperties jwtConfigProperties;

    private Key key;

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtConfigProperties.getSecretKey());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(String userName) {
        Claims claims = Jwts.claims();
        claims.put("userName", userName);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (10000 * 60 * jwtConfigProperties.getExpireMinute())))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getToken(ServerHttpRequest request){
        return request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
    }

    public String getLoginId(String token, String secretKey) {
        return extractClaims(token, secretKey).get("loginId").toString();
    }

    private Claims parseClaims(String accessToken){
        try {
            return Jwts.parserBuilder().setSigningKey(jwtConfigProperties.getSecretKey()).build().parseClaimsJws(accessToken).getBody();
        } catch(ExpiredJwtException e) {
            return e.getClaims();
        }
    }
    // SecretKey를 사용해 Token Parsing
    private Claims extractClaims(String token, String secretKey) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }

    public boolean validateJwtToken(String authToken) throws JwtException {
        try {
            Jwts.parserBuilder().setSigningKey(jwtConfigProperties.getSecretKey()).build().parseClaimsJws(authToken);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.error("INVALID JWT SIGNATURE.");
        } catch (ExpiredJwtException e) {
            log.error("EXPIRED JWT TOKEN.");
        } catch (UnsupportedJwtException e) {
            log.error("UNSUPPORTED JWT TOKEN.");
        } catch (IllegalArgumentException e) {
            log.error("JWT TOKEN IS INVALID.");
        }
        return false;
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        Collection<? extends GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        UserInfo user = UserInfo.builder()
                .userName(claims.get("userName").toString())
                .build();

        return new UsernamePasswordAuthenticationToken(user, "", authorities);
    }

}
