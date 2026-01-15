package com.vmr.cementerio.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.vmr.cementerio.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import com.vmr.cementerio.repository.CiudadanoRepository;
import com.vmr.cementerio.repository.UsuarioRepository;
import com.vmr.cementerio.model.Rol;
import com.vmr.cementerio.model.Usuario;
import com.vmr.cementerio.model.Ciudadano;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner{
    
    private final RolRepository rolRepository;
    private final CiudadanoRepository ciudadanoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Rol rolAdmin = new Rol();
        rolAdmin.setNombre("ADMIN");
        rolRepository.save(rolAdmin);

        Rol rolCiudadano = new Rol();
        rolCiudadano.setNombre("CIUDADANO");
        rolRepository.save(rolCiudadano);

        Usuario admin = new Usuario();
        admin.setNombre("Administrador");
        admin.setEmail("admin@admin.com");
        admin.setContrasena(passwordEncoder.encode("admin"));
        admin.setRol(rolAdmin);
        usuarioRepository.save(admin);

        Ciudadano ciudadano = new Ciudadano();
        ciudadano.setNombre("Ciudadano");
        ciudadano.setEmail("ciudadano@ciudadano.com");
        ciudadano.setContrasena(passwordEncoder.encode("ciudadano"));
        ciudadano.setRol(rolCiudadano);
        ciudadanoRepository.save(ciudadano);

    }

}
