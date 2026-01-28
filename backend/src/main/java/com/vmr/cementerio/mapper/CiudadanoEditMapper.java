package com.vmr.cementerio.mapper;

import org.mapstruct.Mapper;
import com.vmr.cementerio.dto.response.CiudadanoEditDTO;
import com.vmr.cementerio.model.Ciudadano;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CiudadanoEditMapper {
    
    void updateEntityFromDTO(CiudadanoEditDTO ciudadanoDTO, @MappingTarget Ciudadano ciudadano);

    CiudadanoEditDTO toDTO(Ciudadano ciudadano);

}
