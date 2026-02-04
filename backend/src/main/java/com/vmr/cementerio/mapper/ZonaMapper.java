package com.vmr.cementerio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vmr.cementerio.dto.response.ZonaDTO;
import com.vmr.cementerio.model.Zona;

@Mapper(componentModel = "spring")
public interface ZonaMapper {
    
    @Mapping(source = "filas", target = "filas")
    @Mapping(source = "columnas", target = "columnas")
    @Mapping(source = "precio", target = "precio")
    @Mapping(source = "capacidadParcelas", target = "capacidadParcelas")
    Zona toEntity(ZonaDTO zonaDTO);

    @Mapping(source = "filas", target = "filas")
    @Mapping(source = "columnas", target = "columnas")
    @Mapping(source = "precio", target = "precio")
    @Mapping(source = "capacidadParcelas", target = "capacidadParcelas")
    ZonaDTO toDTO(Zona zona);

}
