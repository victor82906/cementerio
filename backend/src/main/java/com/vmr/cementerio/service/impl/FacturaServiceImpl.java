package com.vmr.cementerio.service.impl;

import com.vmr.cementerio.service.FacturaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;
import org.springframework.stereotype.Service;
import com.vmr.cementerio.model.Concesion;
import com.vmr.cementerio.model.Parcela;
import com.vmr.cementerio.model.Servicio;
import com.vmr.cementerio.model.Ciudadano;
import com.vmr.cementerio.model.Ayuntamiento;
import com.vmr.cementerio.repository.FacturaRepository;
import com.vmr.cementerio.model.Factura;
import com.vmr.cementerio.model.Tarifa;
import java.util.Set;
import java.util.List;
import com.vmr.cementerio.dto.response.FacturaDTO;
import com.vmr.cementerio.mapper.FacturaMapper;

@Service
@RequiredArgsConstructor
public class FacturaServiceImpl implements FacturaService{
    
    private final FacturaRepository facturaRepository;
    private final FacturaMapper facturaMapper;

    public Factura findById(Long id){
        return facturaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Factura no encontrada"));
    }

    public List<FacturaDTO> findByAyuntamientoId(Long id){
        return facturaRepository.findDistinctByConcesion_Parcela_Zona_Cementerio_Ayuntamiento_IdOrServicio_Parcela_Zona_Cementerio_Ayuntamiento_Id(id, id)
                .stream()
                .map(facturaMapper::toDTO)
                .toList();
    }

    public List<FacturaDTO> findByCiudadanoId(Long id){
        return facturaRepository.findDistinctByConcesion_Ciudadano_IdOrServicio_Parcela_Concesion_Ciudadano_Id(id, id)
                .stream()
                .map(facturaMapper::toDTO)
                .toList();
    }

    @Transactional
    public Factura saveConcesion(Concesion concesion){
        Parcela parcela = concesion.getParcela();
        Ciudadano ciudadano = concesion.getCiudadano();
        Ayuntamiento ayuntamiento = parcela.getZona().getCementerio().getAyuntamiento();
        Factura factura = new Factura();
        factura.setImporte(parcela.getZona().getPrecio());
        factura.setFecha(LocalDate.now());
        factura.setNombre(ciudadano.getNombre());
        factura.setApellidos(ciudadano.getApellidos());
        factura.setTelefono(ciudadano.getTelefono());
        factura.setCodigoAyto(ayuntamiento.getCodigo());
        factura.setNombreAyto(ayuntamiento.getNombre());
        factura.setConcepto("Concesion de parcela Nº " + parcela.getNumero() + 
                            " de la zona " + parcela.getZona().getCoordenadas() + 
                            " en cemenetrio " + parcela.getZona().getCementerio().getNombre());
        factura.setConcesion(concesion);
        facturaRepository.save(factura);
        return factura;
    }

    @Transactional
    public Factura saveServicio(Servicio servicio){
        Ciudadano ciudadano = servicio.getParcela().getConcesion().getCiudadano();
        Ayuntamiento ayuntamiento = servicio.getParcela().getZona().getCementerio().getAyuntamiento();
        Set<Tarifa> tarifasCementerio = servicio.getParcela().getZona().getCementerio().getTarifas();
        Double precioServicio = tarifasCementerio.stream().filter(t -> t.getTipoServicio().getId().equals(servicio.getTipoServicio().getId()))
                                                .findFirst()
                                                .map(Tarifa::getPrecio)
                                                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado la tarifa"));
        Factura factura = new Factura();
        factura.setImporte(precioServicio);
        factura.setFecha(LocalDate.now());
        factura.setNombre(ciudadano.getNombre());
        factura.setApellidos(ciudadano.getApellidos());
        factura.setTelefono(ciudadano.getTelefono());
        factura.setCodigoAyto(ayuntamiento.getCodigo());
        factura.setNombreAyto(ayuntamiento.getNombre());
        factura.setConcepto("Servicio de " + servicio.getTipoServicio().getNombre() + 
                            " en parcela Nº " + servicio.getParcela().getNumero() + 
                            " de la zona " + servicio.getParcela().getZona().getCoordenadas() + 
                            " en cemenetrio " + servicio.getParcela().getZona().getCementerio().getNombre());
        factura.setServicio(servicio);
        facturaRepository.save(factura);
        return factura;
    }

}
