package com.vmr.cementerio.service.impl;

import org.springframework.stereotype.Service;
import com.vmr.cementerio.mapper.ConcesionMapper;
import com.vmr.cementerio.repository.ConcesionRepository;
import lombok.RequiredArgsConstructor;
import com.vmr.cementerio.model.Ciudadano;
import com.vmr.cementerio.model.Concesion;
import com.vmr.cementerio.dto.response.ConcesionDTO;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import com.vmr.cementerio.model.Parcela;
import com.vmr.cementerio.repository.ParcelaRepository;
import com.vmr.cementerio.repository.CiudadanoRepository;
import com.vmr.cementerio.service.ConcesionService;

@Service
@RequiredArgsConstructor
public class ConcesionServiceImpl implements ConcesionService{
    
    private final ConcesionRepository concesionRepository;
    private final ConcesionMapper concesionMapper;
    private final ParcelaRepository parcelaRepository;
    private final CiudadanoRepository ciudadanoRepository;

    public List<ConcesionDTO> getAll(){
        return concesionRepository.findAll()
                .stream()
                .map(concesionMapper::toDTO)
                .toList();
    }

    public ConcesionDTO findById(Long id){
        return concesionMapper.toDTO(concesionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Concesión no encontrada")));
    }

    public ConcesionDTO save(Long parcelaId, ConcesionDTO concesionDTO){
        Parcela parcela = parcelaRepository.findById(parcelaId)
                .orElseThrow(() -> new EntityNotFoundException("Parcela no encontrada"));
        Ciudadano ciudadano = ciudadanoRepository.findById(concesionDTO.getCiudadanoId())
                .orElseThrow(() -> new EntityNotFoundException("Ciudadano no encontrado"));

        Concesion concesion = concesionMapper.toEntity(concesionDTO);
        concesion.setParcela(parcela);
        concesion.setCiudadano(ciudadano);
        return concesionMapper.toDTO(concesionRepository.save(concesion));
    }

    public ConcesionDTO update(Long id, ConcesionDTO concesionDTO){
        Concesion concesion = concesionRepository.findById(id)  
                .orElseThrow(() -> new EntityNotFoundException("Concesión no encontrada"));

        concesion = concesionMapper.toEntity(concesionDTO);
        return concesionMapper.toDTO(concesionRepository.save(concesion));
    }

    public void delete(Long id){
        if(concesionRepository.existsById(id)){
            concesionRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Concesión no encontrada");
        }
    }

    public ConcesionDTO findByParcelaId(Long id){
        return concesionMapper.toDTO(concesionRepository.findByParcelaId(id)
                .orElseThrow(() -> new EntityNotFoundException("Concesión no encontrada")));
    }
    
    public List<ConcesionDTO> findByCiudadanoId(Long id){
        return concesionRepository.findByCiudadanoId(id)
                .stream()
                .map(concesionMapper::toDTO)
                .toList();
    }


}
