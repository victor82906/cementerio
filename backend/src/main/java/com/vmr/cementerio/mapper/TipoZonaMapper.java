package com.vmr.cementerio.mapper;

import org.mapstruct.Mapper;
import com.vmr.cementerio.dto.response.TipoZonaDTO;
import com.vmr.cementerio.model.TipoZona;

@Mapper(componentModel = "spring")
public interface TipoZonaMapper {

    TipoZona toEntity(TipoZonaDTO tipoZonaDTO);

    TipoZonaDTO toDTO(TipoZona tipoZona);

}
