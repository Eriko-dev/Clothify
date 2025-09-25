package com.example.clothify.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY = "mySuperSecretKeyForJwt1234567890"; // tối thiểu 256-bit

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Tạo token
    public String generateToken(String email) {
        long expirationMillis = 1000 * 60 * 60 * 24; // 24h
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Lấy Claims từ token
    private Claims getClaims(String token) {
        return Jwts.parser()    // tạo JwtParserBuilder
                .setSigningKey(getSigningKey()) // đặt key
                .build()                      // build parser
                .parseClaimsJws(token)        // parse token
                .getBody();                   // lấy body (Claims)
    }

    // Lấy email từ token
    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    // Kiểm tra token hợp lệ
    public boolean isTokenValid(String token, String email) {
        final String tokenEmail = extractEmail(token);
        return (tokenEmail.equals(email) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
}
