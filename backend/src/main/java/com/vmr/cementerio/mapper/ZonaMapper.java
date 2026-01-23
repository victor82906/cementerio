package com.vmr.cementerio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vmr.cementerio.dto.response.ZonaDTO;
import com.vmr.cementerio.model.Zona;

@Mapper(componentModel = "spring")
public interface ZonaMapper {
    
    @Mapping(target = "parcelas", ignore = true)
    Zona toEntity(ZonaDTO zonaDTO);

    ZonaDTO toDTO(Zona zona);

}
