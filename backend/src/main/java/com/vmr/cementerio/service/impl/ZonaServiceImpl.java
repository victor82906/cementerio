package com.vmr.cementerio.service.impl;

import com.vmr.cementerio.repository.ZonaRepository;
import com.vmr.cementerio.repository.CementerioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.vmr.cementerio.mapper.ZonaMapper;
import com.vmr.cementerio.model.Cementerio;
import com.vmr.cementerio.model.Zona;
import com.vmr.cementerio.dto.response.ZonaDTO;
import java.util.List;
import org.springframework.stereotype.Service;
import com.vmr.cementerio.service.ZonaService;
import com.vmr.cementerio.service.ParcelaService;
import com.vmr.cementerio.dto.response.CementerioDTO;
import com.vmr.cementerio.mapper.CementerioMapper;

@Service
@RequiredArgsConstructor
public class ZonaServiceImpl implements ZonaService{
    
    private final ZonaRepository zonaRepository;
    private final ZonaMapper zonaMapper;
    private final CementerioRepository cementerioRepository;
    private final ParcelaService parcelaService;
    private final CementerioMapper cementerioMapper;

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

    @Transactional
    public ZonaDTO save(Long cementerioId, ZonaDTO zonaDTO){
        Cementerio cementerio = cementerioRepository.findById(cementerioId)
                .orElseThrow(() -> new EntityNotFoundException("Cementerio no encontrado"));

        Zona zona = zonaMapper.toEntity(zonaDTO);
        zona.setCementerio(cementerio);
        zonaRepository.save(zona);
        parcelaService.generarParcelasParaZona(zona);
        return zonaMapper.toDTO(zona);
    }

    @Transactional
    public ZonaDTO update(Long id, ZonaDTO zonaDTO){
        Zona zona = zonaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Zona no encontrada"));

        Cementerio cementerio = zona.getCementerio();
        zona = zonaMapper.toEntity(zonaDTO);
        zona.setCementerio(cementerio);
        zonaRepository.save(zona);
        return zonaMapper.toDTO(zona);
    }

    @Transactional
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

    public CementerioDTO findCementerioByZonaId(Long zonaId){
        Zona zona = zonaRepository.findById(zonaId)
                .orElseThrow(() -> new EntityNotFoundException("Zona no encontrada"));

        return cementerioMapper.toDTO(zona.getCementerio());
    }

}
