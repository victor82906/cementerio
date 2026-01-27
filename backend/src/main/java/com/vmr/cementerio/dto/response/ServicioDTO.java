package com.vmr.cementerio.dto.response;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
public class ServicioDTO {
    
    private Long id;

    @NotNull(message = "La fecha es obligatoria")
    @PastOrPresent(message = "La fecha debe ser hoy o anterior")
    private LocalDate fecha;

    private TipoServicioDTO tipoServicio;

}
