package com.vmr.cementerio.security;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.vmr.cementerio.repository.UsuarioRepository;
import com.vmr.cementerio.model.Usuario;

@Component
@RequiredArgsConstructor
public class AuthenticationFacade {
    
    private final UsuarioRepository usuarioRepository;

    public Usuario getAuthenticatedUsuario() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado."));
    }

}
