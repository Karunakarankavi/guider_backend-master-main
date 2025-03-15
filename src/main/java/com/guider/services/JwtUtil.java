package com.guider.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Service;
import com.guider.entity.User;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtUtil {

    // Generate a secure key
	private static final String SECRET_STRING = "kavithaenterprisefristprojectastechleadersoiamexisted";
	private static final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET_STRING.getBytes());
    private static final long EXPIRY_DURATION = 60 * 60 * 1000; // 1 hour in milliseconds

    public String generateToken(User user) {
    	System.out.println(user.getUsername());
    	System.out.println(user.getPassword());
        long currentTimeMillis = System.currentTimeMillis();
        Date issuedAt = new Date(currentTimeMillis);
        Date expiryAt = new Date(currentTimeMillis + EXPIRY_DURATION);

        // Use a Map for claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("issuer", user.getUsername());
        claims.put("issuedAt", issuedAt);
        claims.put("expiration", expiryAt);
        claims.put("userName", user.getUsername());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(issuedAt)
                .setExpiration(expiryAt)
                .signWith(SECRET_KEY) // Secure signing key
                .compact();
    }

    // Method to validate the token
    public Claims validateToken(String token) throws Exception {
        try {
            // Parse the token and verify its signature
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY) // Use the same secret key
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // Check if the token is expired
            Date expiration = claims.getExpiration();
            if (expiration.before(new Date())) {
                throw new Exception("Token has expired");
            }

            // Return the claims if the token is valid
            return claims;
        } catch (ExpiredJwtException ex) {
            throw new Exception("Token has expired", ex);
        } catch (UnsupportedJwtException ex) {
            throw new Exception("Unsupported JWT token", ex);
        } catch (MalformedJwtException ex) {
            throw new Exception("Malformed JWT token", ex);
        } catch (SignatureException ex) {
            throw new Exception("Invalid JWT signature", ex);
        } catch (IllegalArgumentException ex) {
            throw new Exception("Invalid JWT token", ex);
        }
    }
}