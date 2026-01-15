package com.vmr.cementerio.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.vmr.cementerio.repository.CiudadanoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.vmr.cementerio.model.Ciudadano;

@Service
@RequiredArgsConstructor
public class CustomCiudadanoDetailsService implements UserDetailsService {

    private final CiudadanoRepository ciudadanoRepository;

    @Override
    public Ciudadano loadUserByUsername(String email) {
        return ciudadanoRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Ciudadano no encontrado"));
    }

}
