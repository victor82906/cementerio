package com.vmr.cementerio.mapper;

import com.vmr.cementerio.model.Factura;
import com.vmr.cementerio.dto.response.FacturaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FacturaMapper {
    
    Factura toEntity(Factura factura);

    FacturaDTO toDTO(Factura factura);

}
