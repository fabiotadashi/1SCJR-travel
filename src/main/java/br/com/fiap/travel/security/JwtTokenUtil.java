package br.com.fiap.travel.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expirationInSeconds}")
    private int expirationInSeconds;

    public String generateToken(String username){
        LocalDateTime now = LocalDateTime.now();
        Date issuedAt = dateFromLocalDateTime(now);
        Date expiration = dateFromLocalDateTime(now.plusSeconds(expirationInSeconds));

        return Jwts.builder()
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .setSubject(username)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public String getUsernameFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private Date dateFromLocalDateTime(LocalDateTime now) {
        return Date.from(now.toInstant(OffsetDateTime.now().getOffset()));
    }


}
