package com.vmr.cementerio.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;
import com.vmr.cementerio.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;
import com.vmr.cementerio.mapper.UsuarioMapper;
import com.vmr.cementerio.model.Usuario;
import com.vmr.cementerio.dto.response.UsuarioDTO;
import com.vmr.cementerio.service.UsuarioService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Map;
import com.vmr.cementerio.dto.response.CambiarContrasenaDTO;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;
    
    @Value("${ruta.fotos}")
    private String rutaFotos;

    @Override
    public List<UsuarioDTO> getAll(){
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDTO)
                .toList();
    }

    @Override
    public UsuarioDTO findByEmail(String email){
        return usuarioMapper.toDTO(usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado.")));
    }

    @Override
    @Transactional
    public String guardarFoto(Long id, MultipartFile archivo) throws IOException {
        // 1. Buscamos al usuario o lanzamos excepción si no existe
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario con ID " + id + " no encontrado"));

        // 2. Validación manual: solo imágenes
        if (archivo.getContentType() == null || !archivo.getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("El archivo debe ser una imagen válida (JPG/PNG)");
        }

        // 3. Gestión de archivos (Crear directorio si no existe)
        Path directorioPath = Paths.get(rutaFotos);
        if (!Files.exists(directorioPath)) {
            Files.createDirectories(directorioPath);
        }

        // 4. Borrar foto anterior para ahorrar espacio
        if (usuario.getFoto() != null) {
            Files.deleteIfExists(directorioPath.resolve(usuario.getFoto()));
        }

        // 5. Generar nombre único y guardar
        String extension = archivo.getOriginalFilename().substring(archivo.getOriginalFilename().lastIndexOf("."));
        String nombreArchivo = "usuario_" + id + "_" + System.currentTimeMillis() + extension;
        Files.copy(archivo.getInputStream(), directorioPath.resolve(nombreArchivo));

        // 6. Actualizar BD
        usuario.setFoto(nombreArchivo);
        usuarioRepository.save(usuario);

        return nombreArchivo;
    }

    @Override
    public UsuarioDTO findById(Long id){
        return usuarioMapper.toDTO(usuarioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Usuario con ID " + id + " no encontrado")));
    }

    @Override
    @Transactional
    public ResponseEntity<Map<String, String>> cambiarContrasena(Long id, CambiarContrasenaDTO cambiarContrasenaDTO){
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Usuario con ID " + id + " no encontrado"));

        if(!passwordEncoder.matches(cambiarContrasenaDTO.getContrasenaActual(), usuario.getContrasena())){
            throw new IllegalArgumentException("La contraseña actual es incorrecta"); 
        }

        usuario.setContrasena(passwordEncoder.encode(cambiarContrasenaDTO.getContrasena()));
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(Map.of("mensaje", "Contraseña cambiada correctamente"));
    }

}
