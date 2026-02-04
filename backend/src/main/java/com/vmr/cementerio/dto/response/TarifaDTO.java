package com.vmr.cementerio.dto.response;

import lombok.Data;
import jakarta.validation.constraints.Min;
import com.vmr.cementerio.model.Cementerio;

@Data
public class TarifaDTO {
    
    private Long id;

    @Min(value = 1, message = "El precio no puede ser menor a 1")
    private double precio;

    private TipoServicioDTO tipoServicio;

    private Cementerio cementerio;

}
