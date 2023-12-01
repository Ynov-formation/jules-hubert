package com.ynov.security.services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {
    @org.springframework.beans.factory.annotation.Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /***** generate token without claims ******/
    public String generateToken(UserDetails userDetails){

        return generateToken(new HashMap<>(), userDetails);
    }

    /***** generate token from claims ******/
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails){
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    //refactoring the method to generate an access-token and a refresh token
    public String buildToken( Map<String, Object> extraClaims,
                               UserDetails userDetails, long expiration){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .claim("roles", userDetails.getAuthorities())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //to verify that's this token it's for the user passed on parameter
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    // to be sure that's the date of expiration it's after the current date
    public boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {

        return extractClaim(token, Claims::getExpiration);
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
