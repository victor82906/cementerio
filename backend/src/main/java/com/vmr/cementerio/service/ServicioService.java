package com.vmr.cementerio.service;

import org.springframework.stereotype.Service;

import com.vmr.cementerio.model.Parcela;
import com.vmr.cementerio.repository.ServicioRepository;
import lombok.RequiredArgsConstructor;
import com.vmr.cementerio.mapper.ServicioMapper;
import com.vmr.cementerio.model.Servicio;
import com.vmr.cementerio.model.TipoServicio;
import com.vmr.cementerio.repository.TipoServicioRepository;
import com.vmr.cementerio.repository.ParcelaRepository;
import com.vmr.cementerio.dto.response.ServicioDTO;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioService {
    
    private final ServicioRepository servicioRepository;
    private final ServicioMapper servicioMapper;
    private final TipoServicioRepository tipoServicioRepository;
    private final ParcelaRepository parcelaRepository;

    public List<ServicioDTO> getAll(){
        return servicioRepository.findAll()
                .stream()
                .map(servicioMapper::toDTO)
                .toList();
    }

    public ServicioDTO findById(Long id){
        return servicioMapper.toDTO(servicioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Servicio no encontrado")));
    }

    public ServicioDTO save(ServicioDTO servicioDTO){
        Servicio servicio = servicioMapper.toEntity(servicioDTO);
        TipoServicio tipoServicio = tipoServicioRepository.findById(servicioDTO.getTipoServicioId())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de servicio no encontrado"));
        Parcela parcela = parcelaRepository.findById(servicioDTO.getParcelaId())
                .orElseThrow(() -> new EntityNotFoundException("Parcela no encontrada"));

        servicio.setTipoServicio(tipoServicio);
        servicio.setParcela(parcela);
        return servicioMapper.toDTO(servicioRepository.save(servicio));
    }

    public ServicioDTO update(Long id, ServicioDTO servicioDTO){
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Servicio no encontrado"));

        servicio = servicioMapper.toEntity(servicioDTO);
        return servicioMapper.toDTO(servicioRepository.save(servicio));
    }

    public void delete(Long id){
        if(servicioRepository.existsById(id)){
            servicioRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Servicio no encontrado");
        }
    }


}
