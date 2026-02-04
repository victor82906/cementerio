package com.vmr.cementerio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vmr.cementerio.model.Cementerio;
import com.vmr.cementerio.dto.response.CementerioDTO;

@Mapper(componentModel = "spring")
public interface CementerioMapper {
    
    @Mapping(target = "ayuntamiento", ignore = true)
    @Mapping(source = "tiempoExhumacion", target = "tiempoExhumacion") // al ser int hay que especificarlo
    Cementerio toEntity(CementerioDTO cementerioDTO);

    @Mapping(source = "tiempoExhumacion", target = "tiempoExhumacion")
    CementerioDTO toDTO(Cementerio cementerio);

}
