package com.vmr.cementerio.service.impl;

import com.vmr.cementerio.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

import com.vmr.cementerio.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUsuarioDetailsService implements UserDetailsService {
    
    private final UsuarioRepository usuarioRepository;

    // @Override
    // public Usuario loadUserByUsername(String email) {
    //     return usuarioRepository.findByEmail(email)
    //             .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    // }

    @Override
    @Transactional
    public Usuario loadUserByUsername(String email) {
        System.out.println("🔍 Buscando email en BD: '" + email + "'");
        System.out.println("🔍 Longitud: " + email.length());

        Optional<Usuario> user = usuarioRepository.findByEmail(email);

        if (user.isEmpty()) {
            System.out.println("❌ Email NO encontrado en BD");
            // Lista todos los emails para debug
            usuarioRepository.findAll().forEach(u ->
                    System.out.println("   BD tiene: '" + u.getEmail() + "'"));
        } else {
            System.out.println("✅ Email encontrado");
        }

        return user.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

}
