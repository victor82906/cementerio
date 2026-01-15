package com.vmr.cementerio.service;

import com.vmr.cementerio.repository.UsuarioRepository;
import com.vmr.cementerio.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUsuarioDetailsService implements UserDetailsService {
    
    private final UsuarioRepository usuarioRepository;

    @Override
    public Usuario loadUserByUsername(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

}
