package com.vmr.cementerio.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.vmr.cementerio.repository.UsuarioRepository;
import com.vmr.cementerio.model.Usuario;
import java.util.List;
import com.vmr.cementerio.mapper.UsuarioMapper;
import com.vmr.cementerio.dto.response.UsuarioDTO;


@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public List<UsuarioDTO> getAll(){
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDTO)
                .toList();
    }

    public Usuario findByEmail(String email){
        return usuarioRepository.findByEmail(email).orElse(null);
    }
}
