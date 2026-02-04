package com.vmr.cementerio.service;

import com.vmr.cementerio.model.Concesion;
import com.vmr.cementerio.model.Servicio;
import com.vmr.cementerio.model.Factura;

public interface FacturaService {
    
    public Factura saveConcesion(Concesion concesion);

    public Factura saveServicio(Servicio servicio);

}
