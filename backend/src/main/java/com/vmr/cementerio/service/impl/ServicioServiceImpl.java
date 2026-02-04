package com.vmr.cementerio.service.impl;

import org.springframework.stereotype.Service;
import com.vmr.cementerio.model.Parcela;
import com.vmr.cementerio.repository.ServicioRepository;
import lombok.RequiredArgsConstructor;
import com.vmr.cementerio.mapper.ServicioMapper;
import com.vmr.cementerio.model.Servicio;
import com.vmr.cementerio.repository.ParcelaRepository;
import com.vmr.cementerio.dto.response.ServicioDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
import com.vmr.cementerio.service.ServicioService;
import com.vmr.cementerio.service.FacturaService;
import com.vmr.cementerio.dto.response.FacturaDTO;
import com.vmr.cementerio.model.Factura;
import com.vmr.cementerio.mapper.FacturaMapper;

@Service
@RequiredArgsConstructor
public class ServicioServiceImpl implements ServicioService{
    
    private final ServicioRepository servicioRepository;
    private final ServicioMapper servicioMapper;
    private final ParcelaRepository parcelaRepository;
    private final FacturaService facturaService;
    private final FacturaMapper facturaMapper;

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

    @Transactional
    public FacturaDTO save(Long parcelaId, ServicioDTO servicioDTO){
        Parcela parcela = parcelaRepository.findById(parcelaId)
                .orElseThrow(() -> new EntityNotFoundException("Parcela no encontrada"));

        Servicio servicio = servicioMapper.toEntity(servicioDTO);
        servicio.setParcela(parcela);
        servicio.setFecha(LocalDate.now());
        servicio.setRealizado(false);
        servicioRepository.save(servicio);
        Factura factura = facturaService.saveServicio(servicio);
        servicio.setFactura(factura);
        servicioRepository.save(servicio);
        return facturaMapper.toDTO(factura);
    }

    @Transactional
    public ServicioDTO update(Long id, ServicioDTO servicioDTO){
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Servicio no encontrado"));

        servicio = servicioMapper.toEntity(servicioDTO);
        return servicioMapper.toDTO(servicioRepository.save(servicio));
    }

    @Transactional
    public void delete(Long id){
        if(servicioRepository.existsById(id)){
            servicioRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Servicio no encontrado");
        }
    }

    public List<ServicioDTO> findByParcelaId(Long id){
        return servicioRepository.findByParcelaId(id)
                .stream()
                .map(servicioMapper::toDTO)
                .toList();
    }

    public List<ServicioDTO> getServiciosByAyuntamientoId(Long id){
        return servicioRepository.findByParcela_Zona_Cementerio_Ayuntamiento_Id(id)
                .stream()
                .map(servicioMapper::toDTO)
                .toList();
    }

    @Transactional
    public ServicioDTO updateRealizado(Long id){
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Servicio no encontrado"));
        servicio.setRealizado(true);
        servicioRepository.save(servicio);
        return servicioMapper.toDTO(servicio);
    }

}
