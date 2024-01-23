package com.client.wsRasmooplus.service.impl.tokenAnduser;


import com.client.wsRasmooplus.model.UserCredentials;
import com.client.wsRasmooplus.service.TokenService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class TokenServiceImpl implements TokenService {

    @Value("${webservices.rasplus.jwt.expiration}")
    private String expiration;


    //    private Key key2 = Keys.hmacShaKeyFor(HS256.key().build().getEncoded());
    byte[] keyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();

    // Converter a chave gerada para uma string base64
    String secureKey = java.util.Base64.getEncoder().encodeToString(keyBytes);

    @Override
    public String getToken(Authentication authentication) {

        Date today = new Date();
        Date expirationDate = new Date(today.getTime() + Long.parseLong(expiration));

        UserCredentials user = (UserCredentials) authentication.getPrincipal();
        return Jwts.builder()
                .setIssuer("API Rasmoo Plus")
                .setSubject(user.getId().toString())
                .setIssuedAt(today)
                .expiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secureKey)
                .compact();
    }

    @Override
    public Boolean isValid(String token) {
        try {
            getClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println(e.getCause() + " " + e.getCause());
            return false;
        }
    }

    @Override
    public Long getUserId(String token) {
        Jws<Claims> claims = getClaimsJws(token);
        return Long.parseLong(claims.getBody().getSubject());

    }

    private Jws<Claims> getClaimsJws(String token) {
        Jws<Claims> claims =
                Jwts.parser()
                        .setSigningKey(secureKey)
                        .build()
                        .parseSignedClaims(token);
        return claims;
    }

}
