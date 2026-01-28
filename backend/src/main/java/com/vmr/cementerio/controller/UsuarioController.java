package com.vmr.cementerio.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.vmr.cementerio.dto.response.UsuarioDTO;
import com.vmr.cementerio.service.UsuarioService;
import java.io.IOException;
import jakarta.validation.Valid;
import com.vmr.cementerio.dto.response.CambiarContrasenaDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAll(){
        return ResponseEntity.ok(usuarioService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @PostMapping("/foto/{id}")
    public ResponseEntity<Map<String, String>> subirFoto(@PathVariable Long id, @RequestParam MultipartFile foto) throws IOException {
        String nombreFoto = usuarioService.guardarFoto(id, foto);

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Foto subida correctamente");
        response.put("foto", nombreFoto);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/contrasena/{id}")
    public ResponseEntity<Map<String, String>> cambiarContrasena(@PathVariable Long id, @Valid @RequestBody CambiarContrasenaDTO cambiarContrasenaDTO){
        return usuarioService.cambiarContrasena(id, cambiarContrasenaDTO);
    }

}
