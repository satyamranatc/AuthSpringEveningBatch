package com.example.backend.Config;

import java.util.Date;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private final String  SECRET_KEY = "76b4dcb47ca25b2a69e7c69430a885511021686a9baa25ee3ce71bdc1270faa7";
    private final long EXPIRATION = 1000 * 60 * 60; // 1 hour

    public String generateToken(String username) {
        String token = Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis()+ EXPIRATION))
        .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
        .compact();
        return token;
    }

    public String extractUserName(String token)
    {
        String extractedUserName = Jwts.parserBuilder()
        .setSigningKey(SECRET_KEY)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
        
        return extractedUserName;
    }


    public boolean extractExpiration(String token)
    {
        boolean tokenExpiration = Jwts.parserBuilder()
        .setSigningKey(SECRET_KEY)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getExpiration()
        .before(new Date());
        return tokenExpiration;
    }

    public boolean validateToken(String token) {
        String extractedUsername = extractUserName(token);
        return extractedUsername != null && !extractExpiration(token);
        
    }


    
}
