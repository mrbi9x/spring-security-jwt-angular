package com.bi.auth.authservice.securities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class JwtConfig {
    @Value("${security.jwt.uri:}")
    private String uri;
    @Value("${security.jwt.header:}")
    private String header;
    @Value("${security.jwt.prefix:}")
    private String prefix;
    @Value("${security.jwt.expiration:300}")
    private long expiration;
    @Value("${security.jwt.secret:}")
    private String secret;
}