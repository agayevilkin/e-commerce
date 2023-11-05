package com.example.elcstore.config.security;

import com.example.elcstore.dto.auth.AuthResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

    //todo change secret (*don`t need for now*)

    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    public String generateAccessToken(Claims extraClaims, String username) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(Map<String, UUID> userId, String username) {
        return Jwts
                .builder()
                .setClaims(userId)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public AuthResponse generateToken(GenericUserPrincipal genericUserPrincipal) {
        String accessToken = generateAccessToken(extraClaims(genericUserPrincipal), genericUserPrincipal.getUsername());
        String refreshToken = generateRefreshToken(userId(genericUserPrincipal.getUserId()), genericUserPrincipal.getUsername());

        return AuthResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public UUID extractUserId(String token) {
        Claims claims = extractAllClaims(token);
        return UUID.fromString((String) claims.get("user-id"));
    }

    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        return (List<String>) claims.get("roles");
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean isTokenValid(String token) {
        return !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Claims extraClaims(GenericUserPrincipal userPrincipal) {
        Map<String, Object> claims = new HashMap<>();
        Set<String> userRoles = new HashSet<>();
        for (GrantedAuthority role : userPrincipal.getAuthorities()) {
            userRoles.add("ROLE_" + role.getAuthority());
        }
        claims.put("roles", userRoles);
        claims.put("user-id", userPrincipal.getUserId());
        return Jwts.claims(claims);
    }

    private Map<String, UUID> userId(UUID userId) {
        HashMap<String, UUID> userIdClaim = new HashMap<>(1);
        userIdClaim.put("user-id", userId);
        return userIdClaim;
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
