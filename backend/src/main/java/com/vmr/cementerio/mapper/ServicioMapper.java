package com.vmr.cementerio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vmr.cementerio.dto.response.ServicioDTO;
import com.vmr.cementerio.model.Servicio;

@Mapper(componentModel = "spring")
public interface ServicioMapper {

    public ServicioDTO toDTO(Servicio servicio);

    @Mapping(target = "fecha", ignore = true)
    @Mapping(target = "realizado", ignore = true)
    @Mapping(target = "parcela", ignore = true)
    public Servicio toEntity(ServicioDTO servicioDTO);


}
