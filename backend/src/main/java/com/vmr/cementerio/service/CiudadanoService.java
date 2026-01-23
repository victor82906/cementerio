package com.vmr.cementerio.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.vmr.cementerio.repository.RolRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.vmr.cementerio.model.Rol;
import com.vmr.cementerio.repository.CiudadanoRepository;
import com.vmr.cementerio.enums.TipoRol;
import com.vmr.cementerio.model.Ciudadano;
import com.vmr.cementerio.mapper.CiudadanoMapper;
import com.vmr.cementerio.dto.response.CiudadanoDTO;
import java.util.List;



@Service
@RequiredArgsConstructor
public class CiudadanoService {
    
    private final CiudadanoRepository ciudadanoRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final CiudadanoMapper ciudadanoMapper;


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
        if(ciudadanoRepository.findByEmail(ciudadanoDTO.getEmail()).isPresent()){
            throw new IllegalArgumentException("El correo electrónico ya está en uso.");
        }else{
            Ciudadano ciudadano = ciudadanoMapper.toEntity(ciudadanoDTO);
            ciudadano.setContrasena(passwordEncoder.encode(ciudadano.getContrasena()));
            Rol rolCiudadano = rolRepository.findByNombre(TipoRol.CIUDADANO)
                .orElseThrow(() -> new RuntimeException("Rol de ciudadano no encontrado."));

            ciudadano.setRol(rolCiudadano);
            return ciudadanoMapper.toDTO(ciudadanoRepository.save(ciudadano));
        }
    }

    public CiudadanoDTO update(Long id, CiudadanoDTO ciudadanoDTO){
        Ciudadano ciudadano = ciudadanoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ciudadano no encontrado"));

        ciudadano = ciudadanoMapper.toEntity(ciudadanoDTO);
        return ciudadanoMapper.toDTO(ciudadanoRepository.save(ciudadano));
    }

    public void delete(Long id){
        if(ciudadanoRepository.existsById(id)){
            ciudadanoRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Ciudadano no encontrado");
        }
    }


}
