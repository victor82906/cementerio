package com.vmr.cementerio.service;

import com.vmr.cementerio.model.Concesion;
import com.vmr.cementerio.model.Servicio;
import com.vmr.cementerio.model.Factura;
import com.vmr.cementerio.dto.response.FacturaDTO;
import java.util.List;

public interface FacturaService {
    
    public Factura findById(Long id);

    public List<FacturaDTO> findByAyuntamientoId(Long id);

    public List<FacturaDTO> findByCiudadanoId(Long id);

    public Factura saveConcesion(Concesion concesion);

    public Factura saveServicio(Servicio servicio);

}
