package com.vmr.cementerio.service;

import org.springframework.stereotype.Service;

import com.vmr.cementerio.repository.CementerioRepository;
import com.vmr.cementerio.repository.TarifaRepository;
import lombok.RequiredArgsConstructor;
import com.vmr.cementerio.mapper.TarifaMapper;
import com.vmr.cementerio.model.Cementerio;
import com.vmr.cementerio.model.Tarifa;
import com.vmr.cementerio.dto.response.TarifaDTO;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TarifaService {
    
    private final TarifaRepository tarifaRepository;
    private final TarifaMapper tarifaMapper;
    private final CementerioRepository cementerioRepository;


    public List<TarifaDTO> getAll(){
        return tarifaRepository.findAll()
                .stream()
                .map(tarifaMapper::toDTO)
                .toList();
    }

    public TarifaDTO findById(Long id){
        return tarifaMapper.toDTO(tarifaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarifa no encontrada")));
    }

    public TarifaDTO save(Long cementerioId, TarifaDTO tarifaDTO){
        Cementerio cementerio = cementerioRepository.findById(cementerioId)
                .orElseThrow(() -> new EntityNotFoundException("Cementerio no encontrado"));
        
        Tarifa tarifa = tarifaMapper.toEntity(tarifaDTO);
        tarifa.setCementerio(cementerio);
        return tarifaMapper.toDTO(tarifaRepository.save(tarifa));
    }

    public TarifaDTO update(Long id, TarifaDTO tarifaDTO){
        Tarifa tarifa = tarifaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarifa no encontrada"));

        tarifa = tarifaMapper.toEntity(tarifaDTO);
        return tarifaMapper.toDTO(tarifaRepository.save(tarifa));
    }

    public void delete(Long id){
        if(tarifaRepository.existsById(id)){
            tarifaRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Tarifa no encontrada");
        }
    }

    public List<TarifaDTO> findByCementerioId(Long id){
        return tarifaRepository.findByCementerioId(id)
                .stream()
                .map(tarifaMapper::toDTO)
                .toList();
    }


}
