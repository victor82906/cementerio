package com.vmr.cementerio.mapper;

import org.mapstruct.Mapper;
import com.vmr.cementerio.dto.response.TarifaDTO;
import com.vmr.cementerio.model.Tarifa;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TarifaMapper {

    @Mapping(target = "cementerio", ignore = true)
    @Mapping(source = "precio", target = "precio")
    Tarifa toEntity(TarifaDTO tarifaDTO);

    @Mapping(target = "cementerio", ignore = true)
    @Mapping(source = "precio", target = "precio")
    TarifaDTO toDTO(Tarifa tarifa);

}
