package com.vmr.cementerio.service.impl;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.vmr.cementerio.repository.TipoZonaRepository;
import com.vmr.cementerio.mapper.TipoZonaMapper;
import com.vmr.cementerio.model.TipoZona;
import com.vmr.cementerio.dto.response.TipoZonaDTO;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import com.vmr.cementerio.service.TipoZonaService;

@Service
@RequiredArgsConstructor
public class TipoZonaServiceImpl implements TipoZonaService{
    
    private final TipoZonaRepository tipoZonaRepository;
    private final TipoZonaMapper tipoZonaMapper;

    public List<TipoZonaDTO> getAll(){
        return tipoZonaRepository.findAll()
                .stream()
                .map(tipoZonaMapper::toDTO)
                .toList();
    }

    public TipoZonaDTO findById(Long id){
        return tipoZonaMapper.toDTO(tipoZonaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de zona no encontrada")));
    }

    public TipoZonaDTO save(TipoZonaDTO tipoZonaDTO){
        return tipoZonaMapper.toDTO(tipoZonaRepository.save(tipoZonaMapper.toEntity(tipoZonaDTO)));
    }

    public TipoZonaDTO update(Long id, TipoZonaDTO tipoZonaDTO){
        TipoZona tipoZona = tipoZonaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de zona no encontrada"));

        tipoZona = tipoZonaMapper.toEntity(tipoZonaDTO);
        return tipoZonaMapper.toDTO(tipoZonaRepository.save(tipoZona));
    }

    public void delete(Long id){
        if(tipoZonaRepository.existsById(id)){
            tipoZonaRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Tipo de zona no encontrada");
        }
    }


}
