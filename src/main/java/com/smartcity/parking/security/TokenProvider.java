package com.smartcity.parking.security;

import com.smartcity.parking.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TokenProvider {

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5 * 60 * 60;
    private static final String SECRET = "generateaccesstoken";

    @SneakyThrows
    public static Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
            .setSigningKey(SECRET)
            .parseClaimsJws(token)
            .getBody();

    }

    public static String getUserIdFromToken(String token) {
        return getAllClaimsFromToken(token).get("userId").toString();
    }

    public static Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private static boolean isTokenExpired(String token) {
        log.info(
            "TokenProvider.isTokenExpired - Token expiration time {} ",
            getExpirationDateFromToken(token)
        );
        return getAllClaimsFromToken(token).getExpiration().before(new Date());
    }

    public static String generateToken(User user) {

        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put(Claims.SUBJECT, user.getEmail());
        claims.put(Claims.ISSUED_AT, createdDate);
        claims.put(Claims.EXPIRATION, expirationDate);

        return doGenerateToken(claims);
    }

    private static String doGenerateToken(Map<String, Object> claims) {
        return Jwts.builder()
            .setClaims(claims)
            .signWith(SignatureAlgorithm.HS256, SECRET)
            .compact();
    }

    public static Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

}

