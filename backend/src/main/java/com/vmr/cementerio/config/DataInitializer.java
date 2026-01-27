// package com.vmr.cementerio.config;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Component;
// import com.vmr.cementerio.repository.RolRepository;
// import lombok.RequiredArgsConstructor;

// import com.vmr.cementerio.repository.AyuntamientoRepository;
// import com.vmr.cementerio.repository.CiudadanoRepository;
// import com.vmr.cementerio.repository.UsuarioRepository;
// import com.vmr.cementerio.model.Rol;
// import com.vmr.cementerio.model.Usuario;
// import com.vmr.cementerio.enums.TipoRol;
// import com.vmr.cementerio.model.Ciudadano;
// import com.vmr.cementerio.model.Ayuntamiento;
// import com.vmr.cementerio.model.Cementerio;
// import com.vmr.cementerio.repository.CementerioRepository;
// import java.lang.Long;

// @Component
// @RequiredArgsConstructor
// public class DataInitializer implements CommandLineRunner{
    
//     // private final RolRepository rolRepository;
//     // private final CiudadanoRepository ciudadanoRepository;
//     // private final UsuarioRepository usuarioRepository;
//     // private final PasswordEncoder passwordEncoder;
//     // private final AyuntamientoRepository aytoRepository;
//     // private final CementerioRepository cementerioRepository;


//     @Override
//     public void run(String... args) throws Exception {
//     //     Rol rolAdmin = new Rol();
//     //     rolAdmin.setNombre(TipoRol.ADMIN);
//     //     rolRepository.save(rolAdmin);

//     //     Rol rolCiudadano = new Rol();
//     //     rolCiudadano.setNombre(TipoRol.CIUDADANO);
//     //     rolRepository.save(rolCiudadano);

//     //     Rol rolAyuntamiento = new Rol();
//     //     rolAyuntamiento.setNombre(TipoRol.AYUNTAMIENTO);
//     //     rolRepository.save(rolAyuntamiento);

//         // Rol rolAdmin = rolRepository.findByNombre(TipoRol.ADMIN).orElseThrow(() -> new RuntimeException("Rol de administrador no encontrado"));
//         // Usuario admin = new Usuario();
//         // admin.setNombre("Administrador");
//         // admin.setEmail("admin@admin.com");
//         // admin.setContrasena(passwordEncoder.encode("administrador"));
//         // admin.setRol(rolAdmin);
//         // usuarioRepository.save(admin);

//     //     Ciudadano ciudadano = new Ciudadano();
//     //     ciudadano.setNombre("Ciudadano");
//     //     ciudadano.setEmail("ciudadano@ciudadano.com");
//     //     ciudadano.setContrasena(passwordEncoder.encode("ciudadano"));
//     //     ciudadano.setRol(rolCiudadano);
//     //     ciudadanoRepository.save(ciudadano);

//     //     Ayuntamiento ayto = new Ayuntamiento();
//     //     ayto.setNombre("Ayuntamiento Cartagena");
//     //     ayto.setEmail("ayuntamiento@ayuntamiento.com");
//     //     ayto.setContrasena(passwordEncoder.encode("ayuntamiento"));
//     //     ayto.setRol(rolAyuntamiento);
//     //     ayto.setCodigo("123456");
//     //     aytoRepository.save(ayto);

//     //     Cementerio cementerio = new Cementerio();
//     //     cementerio.setNombre("Dos almas");
//     //     cementerio.setDireccion("Calle inventada");
//     //     cementerio.setAyuntamiento(ayto);
//     //     cementerioRepository.save(cementerio);

//     }

// }
