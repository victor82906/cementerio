package com.vmr.cementerio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.vmr.cementerio.dto.response.ServicioDTO;
import com.vmr.cementerio.model.Servicio;

@Mapper(componentModel = "spring")
public interface ServicioMapper {
    
    @Mapping(target = "tipoServicioId", ignore = true)
    @Mapping(target = "parcelaId", ignore = true)
    public ServicioDTO toDTO(Servicio servicio);

    @Mapping(target = "tipoServicio", ignore = true)
    public Servicio toEntity(ServicioDTO servicioDTO);


}
