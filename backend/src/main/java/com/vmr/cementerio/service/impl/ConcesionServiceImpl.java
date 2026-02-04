package com.vmr.cementerio.service.impl;

import org.springframework.stereotype.Service;
import com.vmr.cementerio.mapper.ConcesionMapper;
import com.vmr.cementerio.mapper.FacturaMapper;
import com.vmr.cementerio.mapper.ServicioMapper;
import com.vmr.cementerio.repository.ConcesionRepository;
import lombok.RequiredArgsConstructor;
import com.vmr.cementerio.model.Ciudadano;
import com.vmr.cementerio.model.Concesion;
import com.vmr.cementerio.dto.response.ConcesionDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
import com.vmr.cementerio.model.Parcela;
import com.vmr.cementerio.repository.ParcelaRepository;
import com.vmr.cementerio.repository.CiudadanoRepository;
import com.vmr.cementerio.service.ConcesionService;
import com.vmr.cementerio.service.FacturaService;
import com.vmr.cementerio.dto.response.FacturaDTO;
import com.vmr.cementerio.model.Factura;
import com.vmr.cementerio.model.Servicio;
import com.vmr.cementerio.model.TipoServicio;
import com.vmr.cementerio.repository.TipoServicioRepository;
import com.vmr.cementerio.service.ServicioService;


@Service
@RequiredArgsConstructor
public class ConcesionServiceImpl implements ConcesionService{
    
    private final ConcesionRepository concesionRepository;
    private final ConcesionMapper concesionMapper;
    private final ParcelaRepository parcelaRepository;
    private final CiudadanoRepository ciudadanoRepository;
    private final FacturaService facturaService;
    private final FacturaMapper facturaMapper;
    private final TipoServicioRepository tipoServicioRepository;
    private final ServicioService servicioService;
    private final ServicioMapper servicioMapper;

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

    @Transactional
    public FacturaDTO save(Long parcelaId, ConcesionDTO concesionDTO){
        Parcela parcela = parcelaRepository.findById(parcelaId)
                .orElseThrow(() -> new EntityNotFoundException("Parcela no encontrada"));
        Ciudadano ciudadano = ciudadanoRepository.findById(concesionDTO.getCiudadanoId())
                .orElseThrow(() -> new EntityNotFoundException("Ciudadano no encontrado"));

        Concesion concesion = concesionMapper.toEntity(concesionDTO);
        concesion.setParcela(parcela);
        concesion.setCiudadano(ciudadano);
        concesion.setFechaInicio(LocalDate.now());
        concesion.setFechaFin(LocalDate.now().plusYears(50));
        concesionRepository.save(concesion);
        Factura factura = facturaService.saveConcesion(concesion);
        concesion.setFactura(factura);
        concesionRepository.save(concesion);
        return facturaMapper.toDTO(factura);
    }

    @Transactional
    public ConcesionDTO update(Long id, ConcesionDTO concesionDTO){
        Concesion concesion = concesionRepository.findById(id)  
                .orElseThrow(() -> new EntityNotFoundException("Concesión no encontrada"));

        concesion = concesionMapper.toEntity(concesionDTO);
        return concesionMapper.toDTO(concesionRepository.save(concesion));
    }

    @Transactional
    public void delete(Long id){
        Concesion concesion = concesionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Concesión no encontrada"));
        concesion.getFactura().setConcesion(null);
        Parcela parcela = concesion.getParcela();
        if(parcela.getDifuntos() != null && !parcela.getDifuntos().isEmpty()){
            TipoServicio exumacion = tipoServicioRepository.findByNombre("Exumación de Restos")
                    .orElseThrow(() -> new EntityNotFoundException("Tipo de servicio no encontrado"));
            Servicio servicio = new Servicio();
            servicio.setTipoServicio(exumacion);
            servicioService.save(parcela.getId(), servicioMapper.toDTO(servicio));
        }
        parcela.setConcesion(null);
        parcela.setOcupada(false);
        
        concesionRepository.delete(concesion);
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
