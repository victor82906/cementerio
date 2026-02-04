package com.vmr.cementerio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.vmr.cementerio.model.Difunto;
import com.vmr.cementerio.dto.response.DifuntoDTO;

@Mapper(componentModel = "spring")
public interface DifuntoMapper {

    @Mapping(target = "fechaEntierro", ignore = true)
    Difunto toEntity(DifuntoDTO difuntoDTO);
    
    @Mapping(source = "parcela.numero", target = "numeroParcela")
    @Mapping(source = "parcela.zona.cementerio.id", target = "cementerioId")
    DifuntoDTO toDTO(Difunto difunto);
    
}
