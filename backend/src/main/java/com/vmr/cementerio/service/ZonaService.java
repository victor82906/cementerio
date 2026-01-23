package com.vmr.cementerio.service;

import com.vmr.cementerio.repository.ZonaRepository;
import com.vmr.cementerio.repository.CementerioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import com.vmr.cementerio.mapper.ZonaMapper;
import com.vmr.cementerio.model.Cementerio;
import com.vmr.cementerio.model.Zona;
import com.vmr.cementerio.dto.response.ZonaDTO;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZonaService {
    
    private final ZonaRepository zonaRepository;
    private final ZonaMapper zonaMapper;
    private final CementerioRepository cementerioRepository;

    public List<ZonaDTO> getAll(){
        return zonaRepository.findAll()
                .stream()
                .map(zonaMapper::toDTO)
                .toList();
    }

    public ZonaDTO findById(Long id){
        return zonaMapper.toDTO(zonaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Zona no encontrada")));
    }

    public ZonaDTO save(Long cementerioId, ZonaDTO zonaDTO){
        Cementerio cementerio = cementerioRepository.findById(cementerioId)
                .orElseThrow(() -> new EntityNotFoundException("Cementerio no encontrado"));

        Zona zona = zonaMapper.toEntity(zonaDTO);
        zona.setCementerio(cementerio);
        return zonaMapper.toDTO(zonaRepository.save(zona));
    }

    public ZonaDTO update(Long id, ZonaDTO zonaDTO){
        Zona zona = zonaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Zona no encontrada"));

        zona = zonaMapper.toEntity(zonaDTO);
        return zonaMapper.toDTO(zonaRepository.save(zona));
    }

    public void delete(Long id){
        if(zonaRepository.existsById(id)){
            zonaRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Zona no encontrada");
        }
    }

    public List<ZonaDTO> findByCementerioId(Long id){
        return zonaRepository.findByCementerioId(id)
                .stream()
                .map(zonaMapper::toDTO)
                .toList();
    }

}
