package com.vmr.cementerio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.vmr.cementerio.model.Parcela;
import com.vmr.cementerio.dto.response.ParcelaDTO;

@Mapper(componentModel = "spring")
public interface ParcelaMapper {

    @Mapping(target = "difuntos", ignore = true)
    @Mapping(target = "concesion", ignore = true)
    Parcela toEntity(ParcelaDTO parcelaDTO);

    ParcelaDTO toDTO(Parcela parcela);


}
