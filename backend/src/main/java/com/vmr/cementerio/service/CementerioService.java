package com.vmr.cementerio.service;

import org.springframework.stereotype.Service;

import com.vmr.cementerio.repository.AyuntamientoRepository;
import com.vmr.cementerio.repository.CementerioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import com.vmr.cementerio.mapper.CementerioMapper;
import com.vmr.cementerio.model.Cementerio;
import com.vmr.cementerio.dto.response.CementerioDTO;
import com.vmr.cementerio.model.Ayuntamiento;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CementerioService {
    
    private final CementerioRepository cementerioRepository;
    private final CementerioMapper cementerioMapper;
    private final AyuntamientoRepository ayuntamientoRepository;

    public List<CementerioDTO> getAll(){
        return cementerioRepository.findAll()
                .stream()
                .map(cementerioMapper::toDTO)
                .toList();
    }

    public List<CementerioDTO> buscaPorNombre(String nombre){
        return cementerioRepository.findByNombreContainingIgnoreCaseOrAyuntamientoNombreContainingIgnoreCase(nombre, nombre)
                .stream()
                .map(cementerioMapper::toDTO)
                .toList();
    }

    public CementerioDTO findById(Long id){
        return cementerioMapper.toDTO(cementerioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cementerio no encontrado")));
    }

    public CementerioDTO save(Long ayuntamientoId, CementerioDTO cementerioDTO){
        Ayuntamiento ayuntamiento = ayuntamientoRepository.findById(ayuntamientoId)
                .orElseThrow(() -> new EntityNotFoundException("Ayuntamiento no encontrado"));

        Cementerio cementerio = cementerioMapper.toEntity(cementerioDTO);
        cementerio.setAyuntamiento(ayuntamiento);
        return cementerioMapper.toDTO(cementerioRepository.save(cementerio));
    }

    public CementerioDTO update(Long id, CementerioDTO cementerioDTO){
        Cementerio cementerio = cementerioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cementerio no encontrado"));

        cementerio = cementerioMapper.toEntity(cementerioDTO);
        return cementerioMapper.toDTO(cementerioRepository.save(cementerio));
    }

    public void delete(Long id){
        if(cementerioRepository.existsById(id)){
            cementerioRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Cementerio no encontrado");
        }
    }

    public List<CementerioDTO> findByAyuntamientoId(Long id){
        return cementerioRepository.findByAyuntamientoId(id)
                .stream()
                .map(cementerioMapper::toDTO)
                .toList();
    }


}
