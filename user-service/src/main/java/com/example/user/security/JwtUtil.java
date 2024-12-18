package com.example.user.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    // Generate a secure key (can be stored in application properties or an environment variable)
    private final SecretKey key = Keys.hmacShaKeyFor("yourSuperSecretKeyHereThatIsAtLeast32CharactersLong".getBytes());

    // Generate JWT Token
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract Claims (to get the email)
    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    // Validate Token
    public boolean isTokenValid(String token, String email) {
        String extractedEmail = extractEmail(token);
        return (email.equals(extractedEmail) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    // Parse claims using the secure key
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
