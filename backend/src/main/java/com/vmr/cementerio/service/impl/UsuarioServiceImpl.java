package com.vmr.cementerio.service.impl;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.vmr.cementerio.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import com.vmr.cementerio.mapper.UsuarioMapper;
import com.vmr.cementerio.dto.response.UsuarioDTO;
import com.vmr.cementerio.service.UsuarioService;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

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
}
