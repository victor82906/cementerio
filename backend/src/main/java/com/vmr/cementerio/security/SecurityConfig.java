package com.vmr.cementerio.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import com.vmr.cementerio.service.impl.CustomUsuarioDetailsService;

import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUsuarioDetailsService customUsuarioDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return 
            http
            .cors(cors -> cors.configurationSource(request -> {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(List.of("http://localhost:4200"));
                config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                config.setAllowedHeaders(List.of("*"));
                return config;
            }))
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests((authorize) -> authorize
                // .anyRequest().permitAll()
                .requestMatchers(HttpMethod.DELETE, "/ayuntamiento/{id}/**").hasRole("ADMIN")
                .requestMatchers("/ayuntamiento/{id}/**").hasAnyRole("AYUNTAMIENTO", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/ciudadano/{id}/**").hasRole("ADMIN")
                .requestMatchers("/ciudadano/{id}/**").hasAnyRole("CIUDADANO", "ADMIN")
                .requestMatchers("/auth/login").permitAll()
                .anyRequest().permitAll()
                // .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            // token expirado seguramente
            .exceptionHandling(exception -> exception
            .authenticationEntryPoint((request, response, authException) -> {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Tu sesión ha terminado, por favor inicia sesión de nuevo.\"}");
            }))   
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean // Para que el controllador de login pueda autenticar al usuario
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
            http.getSharedObject(AuthenticationManagerBuilder.class);
        
        authenticationManagerBuilder
            .userDetailsService(customUsuarioDetailsService)
            .passwordEncoder(passwordEncoder());
            
        return authenticationManagerBuilder.build();
    }

}
