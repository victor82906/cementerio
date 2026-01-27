package com.vmr.cementerio.service.impl;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.vmr.cementerio.repository.DifuntoRepository;
import com.vmr.cementerio.mapper.DifuntoMapper;
import com.vmr.cementerio.model.Difunto;
import com.vmr.cementerio.model.Parcela;
import com.vmr.cementerio.dto.response.DifuntoDTO;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import com.vmr.cementerio.repository.ParcelaRepository;
import com.vmr.cementerio.service.DifuntoService;

@Service
@RequiredArgsConstructor
public class DifuntoServiceImpl implements DifuntoService{
    
    private final DifuntoRepository difuntoRepository;
    private final DifuntoMapper difuntoMapper;
    private final ParcelaRepository parcelaRepository;

    public List<DifuntoDTO> getAll(){
        return difuntoRepository.findAll()
                .stream()
                .map(difuntoMapper::toDTO)
                .toList();
    }

    public DifuntoDTO findById(Long id){
        return difuntoMapper.toDTO(difuntoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Difunto no encontrado")));
    }

    public DifuntoDTO save(Long parcelaId, DifuntoDTO difuntoDTO){
        Parcela parcela = parcelaRepository.findById(parcelaId)
               .orElseThrow(() -> new EntityNotFoundException("Parcela no encontrada"));
        
        Difunto difunto = difuntoMapper.toEntity(difuntoDTO);
        difunto.setParcela(parcela);
        return difuntoMapper.toDTO(difuntoRepository.save(difunto));
    }

    public DifuntoDTO update(Long id, DifuntoDTO difuntoDTO){
        Difunto difunto = difuntoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Difunto no encontrado"));

        difunto = difuntoMapper.toEntity(difuntoDTO);
        return difuntoMapper.toDTO(difuntoRepository.save(difunto));
    }

    public void delete(Long id){
        if(difuntoRepository.existsById(id)){
            difuntoRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Difunto no encontrado");
        }
    }

    public List<DifuntoDTO> findByParcelaId(Long id){
        return difuntoRepository.findByParcelaId(id)
                .stream()
                .map(difuntoMapper::toDTO)
                .toList();
    }

    // public List<DifuntoDTO> findByCiudadanoId(Long id){
    //     return difuntoRepository.findByCiudadanoId(id)
    //             .stream()
    //             .map(difuntoMapper::toDTO)
    //             .toList();
    // }

}
