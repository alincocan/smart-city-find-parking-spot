package com.smartcity.parking.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GenerateToken {
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5 * 60 * 60;
    private static final String SECRET = "generateaccesstoken";
    public static String generateToken(String id, String email) {

        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", id);
        claims.put(Claims.SUBJECT, email);
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

    public static void main(String[] args) {
        System.out.println(generateToken("123456", "testemail"));
    }
}
