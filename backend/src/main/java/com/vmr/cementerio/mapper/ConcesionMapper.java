package com.vmr.cementerio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vmr.cementerio.dto.response.ConcesionDTO;
import com.vmr.cementerio.model.Concesion;

@Mapper(componentModel = "spring")
public interface ConcesionMapper {
    
    @Mapping(target = "parcela", ignore = true)
    @Mapping(target = "ciudadano", ignore = true)
    Concesion toEntity(ConcesionDTO concesionDTO);

    @Mapping(source = "ciudadano.id", target = "ciudadanoId")
    ConcesionDTO toDTO(Concesion concesion);

}
