package com.vmr.cementerio.service;

import java.util.List;
import com.vmr.cementerio.dto.response.UsuarioDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;
import com.vmr.cementerio.dto.response.CambiarContrasenaDTO;

public interface UsuarioService {

    public List<UsuarioDTO> getAll();

    public UsuarioDTO findById(Long id);

    public UsuarioDTO findByEmail(String email);

    public String guardarFoto(Long id, MultipartFile archivo) throws IOException;

    public ResponseEntity<Map<String, String>> cambiarContrasena(Long id, CambiarContrasenaDTO cambiarContrasenaDTO);

}
