package com.vmr.cementerio.security;

import org.springframework.web.filter.OncePerRequestFilter;
import lombok.RequiredArgsConstructor;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.springframework.http.HttpHeaders;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import com.vmr.cementerio.model.Usuario;
import com.vmr.cementerio.service.CustomUsuarioDetailsService;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final JwtService jwtService;
    private final CustomUsuarioDetailsService customUsuarioDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            String jwtToken = authorizationHeader.substring(7);
            String email = jwtService.extractEmail(jwtToken);

            if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
                Usuario usuario = customUsuarioDetailsService.loadUserByUsername(email);
                
                if(jwtService.isTokenValid(jwtToken, usuario)){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        usuario,
                        null,
                        usuario.getAuthorities()
                    );

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    
}
