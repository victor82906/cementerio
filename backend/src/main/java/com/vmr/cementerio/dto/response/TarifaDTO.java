package com.vmr.cementerio.dto.response;

import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Data
public class TarifaDTO {
    
    private Long id;

    @NotBlank(message = "La descripcion de la tarifa es obligatoria")
    private String descripcion;

    @Min(value = 0, message = "El precio no puede ser negativo")
    private double precio;

    private TipoServicioDTO tipoServicio;

}
