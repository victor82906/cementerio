package com.vmr.cementerio.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.vmr.cementerio.repository.RolRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.vmr.cementerio.model.Rol;
import com.vmr.cementerio.repository.CiudadanoRepository;
import com.vmr.cementerio.model.Ciudadano;

@Service
@RequiredArgsConstructor
public class CiudadanoService {
    
    private final CiudadanoRepository ciudadanoRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public Ciudadano registrarUsuario(Ciudadano ciudadano) {
        if(ciudadanoRepository.findByEmail(ciudadano.getEmail()).isPresent()){
            throw new IllegalArgumentException("El correo electrónico ya está en uso.");
        }

        ciudadano.setContrasena(passwordEncoder.encode(ciudadano.getContrasena()));

        Rol rolCiudadano = rolRepository.findByNombre("CIUDADANO")
                .orElseThrow(() -> new RuntimeException("Rol de ciudadano no encontrado."));

        ciudadano.setRol(rolCiudadano);

        return ciudadanoRepository.save(ciudadano);
    }

}
