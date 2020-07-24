package com.bi.auth.authservice.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.bi.auth.authservice.dtos.UserDetailsPrincipal;
import com.bi.auth.authservice.securities.JwtConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtTokenProvider {
    @Autowired
    private JwtConfig jwtConfig;

    public String generateToken(Authentication authen) {
        UserDetailsPrincipal principal = (UserDetailsPrincipal) authen.getPrincipal();
        Long now = System.currentTimeMillis();
        Long expiration = now + jwtConfig.getExpiration() * 1000;
        List<String> authorities = authen.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return Jwts.builder()//
                .setSubject(String.valueOf(principal.getId()))//
                .claim("authorities", authorities)//
                .setIssuedAt(new Date(now))//
                .setExpiration(new Date(expiration))//
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())//
                .compact();
    }

    public Claims getClaimsFromJWT(String jwtToken) {
        return Jwts.parser()//
                .setSigningKey(jwtConfig.getSecret().getBytes()) //
                .parseClaimsJws(jwtToken)//
                .getBody();
    }

    public boolean validateJwtToken(String jwtToken) {
        try {

            Jwts.parser()//
                    .setSigningKey(jwtConfig.getSecret().getBytes())//
                    .parseClaimsJws(jwtToken);
            return true;

        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}