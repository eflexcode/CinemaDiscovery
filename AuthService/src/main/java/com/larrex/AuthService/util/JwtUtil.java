package com.larrex.AuthService.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String key = " am okay with this behaviour . If an user is not authenticated , why bother to worry about telling him more information about your system. Just like if an user does not have permission to view your harddisk , why need to let him can discover your harddisk directory tree structure .";

    public String generateJwt(UserDetails userDetails){

        Map<String,Object> claims = new HashMap<>();

     return Jwts.builder()
                .addClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+Util.JWT_TWO_MONTHS_EXP_TIME))
                .signWith(signInKey(key), SignatureAlgorithm.HS512)
                .compact();

    }

    private Key signInKey(String key){
        byte[] bytesKey = key.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(bytesKey);
    }

}
