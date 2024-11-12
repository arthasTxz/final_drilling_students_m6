package com.edutecno.backend.security.jwt;

import com.edutecno.backend.security.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationMs}")
    private int jwtExpiration;

    public String generateToken(Authentication authentication) {
        Map<String, String> claims = generateClaims(authentication);
        return Jwts.builder()
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(key()).compact();
    }

    private SecretKey key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    private Map<String, String> generateClaims(Authentication authentication) {
        Map<String, String> claims = new HashMap<>();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            claims.put("role", authority.getAuthority());
        }
        claims.put("username", userDetails.getUsername());
        claims.put("email", userDetails.getEmail());
//        claims.put("role", userDetails.getAuthorities().toString());
        return claims;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts
                .parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload().get("username", String.class);
    }

}
