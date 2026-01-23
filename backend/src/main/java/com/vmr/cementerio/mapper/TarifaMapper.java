package com.vmr.cementerio.mapper;

import org.mapstruct.Mapper;
import com.vmr.cementerio.dto.response.TarifaDTO;
import com.vmr.cementerio.model.Tarifa;

@Mapper(componentModel = "spring")
public interface TarifaMapper {

    Tarifa toEntity(TarifaDTO tarifaDTO);

    TarifaDTO toDTO(Tarifa tarifa);

}
