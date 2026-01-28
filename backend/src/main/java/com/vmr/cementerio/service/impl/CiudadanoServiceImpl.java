package com.vmr.cementerio.service.impl;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.vmr.cementerio.repository.RolRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.vmr.cementerio.model.Rol;
import com.vmr.cementerio.repository.CiudadanoRepository;
import com.vmr.cementerio.enums.TipoRol;
import com.vmr.cementerio.model.Ciudadano;
import com.vmr.cementerio.mapper.CiudadanoEditMapper;
import com.vmr.cementerio.mapper.CiudadanoMapper;
import com.vmr.cementerio.dto.response.CiudadanoDTO;
import com.vmr.cementerio.dto.response.CiudadanoEditDTO;
import java.util.List;
import com.vmr.cementerio.service.CiudadanoService;
import com.vmr.cementerio.repository.UsuarioRepository;


@Service
@RequiredArgsConstructor
public class CiudadanoServiceImpl implements CiudadanoService{
    
    private final CiudadanoRepository ciudadanoRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final CiudadanoMapper ciudadanoMapper;
    private final UsuarioRepository usuarioRepository;
    private final CiudadanoEditMapper ciudadanoEditMapper;


    public List<CiudadanoDTO> getAll(){
        return ciudadanoRepository.findAll()
                .stream()
                .map(ciudadanoMapper::toDTO)
                .toList();
    }

    public List<CiudadanoDTO> buscaPorNombre(String nombre){
        return ciudadanoRepository.findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCaseOrEmailContainingIgnoreCase(nombre, nombre, nombre)
                .stream()
                .map(ciudadanoMapper::toDTO)
                .toList();
    }

    public CiudadanoDTO findById(Long id){
        return ciudadanoMapper.toDTO(ciudadanoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ciudadano no encontrado")));
    }

    public CiudadanoDTO save(CiudadanoDTO ciudadanoDTO){
        if(usuarioRepository.findByEmail(ciudadanoDTO.getEmail()).isPresent()){
            throw new IllegalArgumentException("El correo electrónico ya está en uso.");
        }else if(ciudadanoRepository.findByDni(ciudadanoDTO.getDni()).isPresent()){
            throw new IllegalArgumentException("El DNI ya está en uso.");
        }else{
            Ciudadano ciudadano = ciudadanoMapper.toEntity(ciudadanoDTO);
            ciudadano.setContrasena(passwordEncoder.encode(ciudadano.getContrasena()));
            Rol rolCiudadano = rolRepository.findByNombre(TipoRol.CIUDADANO)
                .orElseThrow(() -> new RuntimeException("Rol de ciudadano no encontrado."));

            ciudadano.setRol(rolCiudadano);
            return ciudadanoMapper.toDTO(ciudadanoRepository.save(ciudadano));
        }
    }

    public CiudadanoEditDTO update(Long id, CiudadanoEditDTO ciudadanoDTO){
        Ciudadano ciudadano = ciudadanoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ciudadano no encontrado"));

        ciudadanoEditMapper.updateEntityFromDTO(ciudadanoDTO, ciudadano);
        return ciudadanoEditMapper.toDTO(ciudadanoRepository.save(ciudadano));
    }

    public void delete(Long id){
        if(ciudadanoRepository.existsById(id)){
            ciudadanoRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Ciudadano no encontrado");
        }
    }


}
