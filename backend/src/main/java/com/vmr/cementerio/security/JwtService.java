package com.vmr.cementerio.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtBuilder;
import java.util.Date;
import com.vmr.cementerio.model.Usuario;


@Service
public class JwtService {

    // private final JwtBuilder jwtBuilder;
    private final SecretKey secretKey;

    public JwtService(SecretKey secretKey) {
        // this.jwtBuilder = Jwts.builder();
        this.secretKey = secretKey;
    }

    public String generateToken(Usuario usuario) {
        Instant now = Instant.now();
        Date expiryDate = Date.from(now.plus(1, ChronoUnit.HOURS));
            
        return Jwts.builder()
            // id del usuario
            .subject(String.valueOf(usuario.getId()))
            // La clave secreta para firmar el token y saber que es nuestro cuando lleguen las peticiones del frontend
            .signWith(secretKey)
            // Fecha emisión del token
            .issuedAt(Date.from(now))
            // Fecha de expiración del token
            .expiration(expiryDate)
            // información personalizada: rol o roles, username, email, avatar...
            // .claim("role", user.getRole())
            .claim("email", usuario.getEmail())
            //.claim("avatar", user.getAvatarUrl())
            // Construye el token
            .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
    }

    public boolean isTokenValid(String token, Usuario usuario) {
        String email = extractEmail(token);
        return email.equals(usuario.getEmail()) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getExpiration();
        return expiration.before(new Date());
    }

}
