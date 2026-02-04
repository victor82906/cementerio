package com.vmr.cementerio.dto.response;

import java.time.LocalDate;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ConcesionDTO {
    
    private Long id;

    @PositiveOrZero(message = "El precio no puede ser negativo")
    private double precio;

    // @NotNull(message = "La fecha de inicio es obligatoria")
    // @FutureOrPresent(message = "La fecha de inicio no puede ser en el pasado")
    private LocalDate fechaInicio;

    // @NotNull(message = "La fecha de fin es obligatoria")
    // @Future(message = "La fecha de fin debe ser una fecha futura")
    private LocalDate fechaFin;

    private Long ciudadanoId;

    private ParcelaDTO parcela;

}
