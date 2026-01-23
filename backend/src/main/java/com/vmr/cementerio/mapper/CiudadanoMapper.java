package com.vmr.cementerio.mapper;

import com.vmr.cementerio.dto.response.CiudadanoDTO;
import com.vmr.cementerio.model.Ciudadano;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CiudadanoMapper {

    Ciudadano toEntity(CiudadanoDTO ciudadanoDTO);

    @Mapping(target = "contrasena", ignore = true)
    CiudadanoDTO toDTO(Ciudadano ciudadano);

}
