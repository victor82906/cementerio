package com.vmr.cementerio.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.nio.charset.StandardCharsets;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class JwtConfig {
    
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Bean
    public SecretKey jwtSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

}
