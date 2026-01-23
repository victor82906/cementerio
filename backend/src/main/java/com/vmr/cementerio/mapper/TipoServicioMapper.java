package com.vmr.cementerio.mapper;

import org.mapstruct.Mapper;
import com.vmr.cementerio.dto.response.TipoServicioDTO;
import com.vmr.cementerio.model.TipoServicio;


@Mapper(componentModel = "spring")
public interface TipoServicioMapper {
    
    TipoServicio toEntity(TipoServicioDTO tipoServicioDTO);

    TipoServicioDTO toDTO(TipoServicio tipoServicio);

}
