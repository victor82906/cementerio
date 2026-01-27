package com.vmr.cementerio.service.impl;

import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.vmr.cementerio.dto.response.AyuntamientoDTO;
import com.vmr.cementerio.enums.TipoRol;
import com.vmr.cementerio.mapper.AyuntamientoMapper;
import com.vmr.cementerio.model.Ayuntamiento;
import com.vmr.cementerio.model.Rol;
import com.vmr.cementerio.repository.AyuntamientoRepository;
import com.vmr.cementerio.repository.RolRepository;
import com.vmr.cementerio.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import com.vmr.cementerio.service.AyuntamientoService;

@Service
@RequiredArgsConstructor
public class AyuntamientoServiceImpl implements AyuntamientoService{
    
    private final AyuntamientoRepository ayuntamientoRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final AyuntamientoMapper ayuntamientoMapper;
    private final UsuarioRepository usuarioRepository;
    

    public List<AyuntamientoDTO> getAll(){
        return ayuntamientoRepository.findAll()
                .stream()
                .map(ayuntamientoMapper::toDTO)
                .toList();
    }

    public List<AyuntamientoDTO> buscaPorNombre(String nombre){
        return ayuntamientoRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(ayuntamientoMapper::toDTO)
                .toList();
    }

    public AyuntamientoDTO findById(Long id){
        return ayuntamientoMapper.toDTO(ayuntamientoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ayuntamiento no encontrado")));
    }

    public AyuntamientoDTO save(AyuntamientoDTO ayuntamientoDTO){
        if(usuarioRepository.findByEmail(ayuntamientoDTO.getEmail()).isPresent()){
            throw new IllegalArgumentException("El correo electrónico ya está en uso.");
        }else{
            Ayuntamiento ayuntamiento = ayuntamientoMapper.toEntity(ayuntamientoDTO);
            ayuntamiento.setContrasena(passwordEncoder.encode(ayuntamiento.getContrasena()));
            Rol rolAyuntamiento = rolRepository.findByNombre(TipoRol.AYUNTAMIENTO)
                .orElseThrow(() -> new EntityNotFoundException("Rol de ayuntamiento no encontrado."));

            ayuntamiento.setRol(rolAyuntamiento);
            return ayuntamientoMapper.toDTO(ayuntamientoRepository.save(ayuntamiento));
        }
    }

    public AyuntamientoDTO update(Long id, AyuntamientoDTO ayuntamientoDTO){
        Ayuntamiento ayuntamiento = ayuntamientoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ayuntamiento no encontrado"));

        ayuntamiento = ayuntamientoMapper.toEntity(ayuntamientoDTO);
        return ayuntamientoMapper.toDTO(ayuntamientoRepository.save(ayuntamiento));
    }


    public void delete(Long id){
        if(ayuntamientoRepository.existsById(id)){
            ayuntamientoRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Ayuntamiento no encontrado");
        }
    }

    public Ayuntamiento findByEmail(String email){
        return ayuntamientoRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Ayuntamiento no encontrado"));
    }


}
