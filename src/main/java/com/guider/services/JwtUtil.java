package com.guider.services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import com.guider.entity.User;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtUtil {

    private static final String SECRET_STRING = "kavithaenterprisefristprojectastechleadersoiamexisted";
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET_STRING.getBytes());

    private static final long ACCESS_TOKEN_VALIDITY = 60  * 1000; // 1 hour
    private static final long REFRESH_TOKEN_VALIDITY = 7 * 24 * 60 * 60 * 1000; // 7 days

    public Map<String, String> generateToken(User user) {
        long currentTimeMillis = System.currentTimeMillis();

        Date issuedAt = new Date(currentTimeMillis);
        Date accessTokenExpiry = new Date(currentTimeMillis + ACCESS_TOKEN_VALIDITY);
        Date refreshTokenExpiry = new Date(currentTimeMillis + REFRESH_TOKEN_VALIDITY);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUser_id());
        claims.put("username", user.getUsername());
        claims.put("email", user.getEmail());
        claims.put("scope", "USER");

        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(accessTokenExpiry)
                .claim("token_type", "access_token")
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();

        String refreshToken = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(refreshTokenExpiry)
                .claim("token_type", "refresh_token")
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("access_token", accessToken);
        tokenMap.put("refresh_token", refreshToken);

        return tokenMap;
    }

    public Claims validateToken(String token) throws Exception {

        try {
        	System.out.println("printing token" +token);

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // Optional: distinguish between access and refresh tokens
            System.out.println("Token claims: " + claims);

            String tokenType = claims.get("token_type", String.class);
            System.out.println("getting token type"+tokenType);
            if (tokenType == null || (!tokenType.equals("access_token") && !tokenType.equals("refresh_token"))) {
                throw new Exception("Unknown token type");
            }

            Date expiration = claims.getExpiration();
            if (expiration.before(new Date())) {
                throw new Exception("Token has expired");
            }
            System.out.println(claims);
            return claims;

        } catch (ExpiredJwtException ex) {
            throw new Exception("Token has expired", ex);
        } catch (UnsupportedJwtException | MalformedJwtException |
                 SignatureException | IllegalArgumentException ex) {
            throw new Exception("Invalid JWT token", ex);
        }
    }
}
