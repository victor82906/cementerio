package com.vmr.cementerio.service.impl;

import com.vmr.cementerio.mapper.TipoServicioMapper;
import com.vmr.cementerio.repository.TipoServicioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.vmr.cementerio.dto.response.TipoServicioDTO;
import com.vmr.cementerio.model.TipoServicio;
import java.util.List;
import com.vmr.cementerio.service.TipoServicioService;

@Service
@RequiredArgsConstructor
public class TipoServicioServiceImpl implements TipoServicioService{
    
    private final TipoServicioRepository tipoServicioRepository;
    private final TipoServicioMapper tipoServicioMapper;

    public List<TipoServicioDTO> getAll(){
        return tipoServicioRepository.findAll()
                .stream()
                .map(tipoServicioMapper::toDTO)
                .toList();
    }

    public TipoServicioDTO findById(Long id){
        return tipoServicioMapper.toDTO(tipoServicioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de servicio no encontrado")));
    }

    public TipoServicioDTO save(TipoServicioDTO tipoServicioDTO){
        tipoServicioDTO.setNombre(tipoServicioDTO.getNombre().trim().toUpperCase());
        if(tipoServicioRepository.findByNombre(tipoServicioDTO.getNombre()).isPresent()){
            throw new IllegalArgumentException("El tipo de servicio ya existe.");
        }else{
            return tipoServicioMapper.toDTO(tipoServicioRepository.save(tipoServicioMapper.toEntity(tipoServicioDTO)));
        }
    }

    public TipoServicioDTO update(Long id, TipoServicioDTO tipoServicioDTO){
        TipoServicio tipoServicio = tipoServicioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        tipoServicio = tipoServicioMapper.toEntity(tipoServicioDTO);
        return tipoServicioMapper.toDTO(tipoServicioRepository.save(tipoServicio));
    }

    public void delete(Long id){
        if(tipoServicioRepository.existsById(id)){
            tipoServicioRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Tipo de servicio no encontrado");
        }
    }

}
