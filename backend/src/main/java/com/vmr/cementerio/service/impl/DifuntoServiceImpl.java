package com.vmr.cementerio.service.impl;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.vmr.cementerio.repository.DifuntoRepository;
import com.vmr.cementerio.mapper.DifuntoMapper;
import com.vmr.cementerio.model.Difunto;
import com.vmr.cementerio.model.Parcela;
import com.vmr.cementerio.dto.response.DifuntoDTO;
import com.vmr.cementerio.dto.response.FacturaDTO;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
import com.vmr.cementerio.repository.ParcelaRepository;
import com.vmr.cementerio.repository.TipoServicioRepository;
import com.vmr.cementerio.service.DifuntoService;
import com.vmr.cementerio.service.ServicioService;
import com.vmr.cementerio.model.Servicio;
import com.vmr.cementerio.mapper.ServicioMapper;
import com.vmr.cementerio.dto.response.ParcelaDTO;
import com.vmr.cementerio.mapper.ParcelaMapper;


@Service
@RequiredArgsConstructor
public class DifuntoServiceImpl implements DifuntoService{
    
    private final DifuntoRepository difuntoRepository;
    private final DifuntoMapper difuntoMapper;
    private final ParcelaRepository parcelaRepository;
    private final ServicioService servicioService;
    private final ServicioMapper servicioMapper;
    private final TipoServicioRepository tipoServicioRepository;
    private final ParcelaMapper parcelaMapper;

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

    @Transactional
    public FacturaDTO save(Long parcelaId, DifuntoDTO difuntoDTO){
        Parcela parcela = parcelaRepository.findById(parcelaId)
               .orElseThrow(() -> new EntityNotFoundException("Parcela no encontrada"));

        if(parcela.getZona().getCapacidadParcelas() == parcela.getDifuntos().size()){
            throw new IllegalStateException("Capacidad de parcela completa");
        }
        
        Difunto difunto = difuntoMapper.toEntity(difuntoDTO);
        difunto.setParcela(parcela);
        difunto.setFechaEntierro(LocalDate.now());
        difuntoRepository.save(difunto);
        parcela.getDifuntos().add(difunto);
        if(parcela.getZona().getCapacidadParcelas() == parcela.getDifuntos().size()){
            parcela.setOcupada(true);
        }
        parcelaRepository.save(parcela);
        Servicio servicio = new Servicio();
        servicio.setTipoServicio(tipoServicioRepository.findByNombre("Inhumacion")
                .orElseThrow(() -> new EntityNotFoundException("Tipo de servicio no encontrado")));
        FacturaDTO factura = servicioService.save(parcela.getId(), servicioMapper.toDTO(servicio));
        return factura;
    }

    @Transactional
    public DifuntoDTO update(Long id, DifuntoDTO difuntoDTO){
        Difunto difunto = difuntoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Difunto no encontrado"));

        difunto = difuntoMapper.toEntity(difuntoDTO);
        return difuntoMapper.toDTO(difuntoRepository.save(difunto));
    }

    @Transactional
    public FacturaDTO delete(Long id){
        Difunto difunto = difuntoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Difunto no encontrado"));
        Parcela parcela = difunto.getParcela();
        parcela.getDifuntos().remove(difunto);
        if(parcela.getZona().getCapacidadParcelas() > parcela.getDifuntos().size()){
            parcela.setOcupada(false);
        }
        parcelaRepository.save(parcela);
        difuntoRepository.delete(difunto);
        Servicio servicio = new Servicio();
        servicio.setTipoServicio(tipoServicioRepository.findByNombre("Exhumacion")
                .orElseThrow(() -> new EntityNotFoundException("Tipo de servicio no encontrado")));
        FacturaDTO factura = servicioService.save(parcela.getId(), servicioMapper.toDTO(servicio));
        return factura;
    }

    public List<DifuntoDTO> findByParcelaId(Long id){
        return difuntoRepository.findByParcelaId(id)
                .stream()
                .map(difuntoMapper::toDTO)
                .toList();
    }

    public List<DifuntoDTO> findDifuntosByCiudadanoId(Long id){
        return difuntoRepository.findByParcela_Concesion_Ciudadano_Id(id)
                .stream()
                .map(difuntoMapper::toDTO)
                .toList();
    }

    public ParcelaDTO getParcelaByDifuntoId(Long id){
        Difunto difunto = difuntoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Difunto no encontrado"));
        return parcelaMapper.toDTO(difunto.getParcela());
    }


    // public List<DifuntoDTO> findByCiudadanoId(Long id){
    //     return difuntoRepository.findByCiudadanoId(id)
    //             .stream()
    //             .map(difuntoMapper::toDTO)
    //             .toList();
    // }

}
