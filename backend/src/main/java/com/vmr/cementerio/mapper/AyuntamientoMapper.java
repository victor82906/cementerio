package com.vmr.cementerio.mapper;

import org.mapstruct.Mapper;
import com.vmr.cementerio.dto.response.AyuntamientoDTO;
import com.vmr.cementerio.model.Ayuntamiento;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AyuntamientoMapper {

    Ayuntamiento toEntity(AyuntamientoDTO aytoDTO);

    @Mapping(target = "contrasena", ignore = true)
    AyuntamientoDTO toDTO(Ayuntamiento ayto);
    
}