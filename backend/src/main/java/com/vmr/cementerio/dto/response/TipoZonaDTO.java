package com.vmr.cementerio.dto.response;

import com.vmr.cementerio.enums.TipoParcela;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TipoZonaDTO {

    private Long id;

    @NotNull(message = "El nombre es obligatorio")
    private TipoParcela nombre;

}
