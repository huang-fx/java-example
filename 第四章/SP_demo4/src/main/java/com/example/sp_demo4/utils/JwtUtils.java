package com.example.sp_demo4.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
    private static String signStr = System.getenv("JWT_SECRET");
    private static Key sign = Keys.hmacShaKeyFor(signStr.getBytes(StandardCharsets.UTF_8));
    public String getToken(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("a", "123");
        map.put("id", id);
        String token = Jwts.builder()
        .setHeaderParam("test", "asd123")
        .setClaims(map)
        .setSubject(id)
        .setExpiration(new Date(System.currentTimeMillis() + 24 * 3600 * 100))
        .signWith(sign)
        .compact();
        return token;
    }

    public boolean verifyToken(String token) {
        Claims claims = Jwts.parser()
        .verifyWith((SecretKey)sign)            // 这里传你的 SecretKey
        .build()
        .parseSignedClaims(token)
        .getPayload();
        if (claims.getExpiration().before(new Date())) {
            return false;
        }
        return true;
    }
}
