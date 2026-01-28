package com.vmr.cementerio.mapper;

import org.mapstruct.Mapper;
import com.vmr.cementerio.dto.response.AyuntamientoEditDTO;
import com.vmr.cementerio.model.Ayuntamiento;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AyuntamientoEditMapper {
    
    void updateEntityFromDTO(AyuntamientoEditDTO ayuDTO, @MappingTarget Ayuntamiento ayu);

    AyuntamientoEditDTO toDTO(Ayuntamiento ayu);

}
