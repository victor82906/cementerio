package com.vmr.cementerio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vmr.cementerio.model.Cementerio;
import com.vmr.cementerio.dto.response.CementerioDTO;

@Mapper(componentModel = "spring")
public interface CementerioMapper {
    
    @Mapping(target = "ayuntamiento", ignore = true)
    @Mapping(target = "zonas", ignore = true)
    Cementerio toEntity(CementerioDTO cementerioDTO);

    CementerioDTO toDTO(Cementerio cementerio);

}
