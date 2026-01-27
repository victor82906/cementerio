package com.vmr.cementerio.mapper;

import org.mapstruct.Mapper;
import com.vmr.cementerio.dto.response.ServicioDTO;
import com.vmr.cementerio.model.Servicio;

@Mapper(componentModel = "spring")
public interface ServicioMapper {

    public ServicioDTO toDTO(Servicio servicio);

    public Servicio toEntity(ServicioDTO servicioDTO);


}
