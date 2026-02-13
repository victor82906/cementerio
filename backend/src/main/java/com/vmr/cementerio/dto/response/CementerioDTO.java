package com.vmr.cementerio.dto.response;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CementerioDTO {
    
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre es demasiado largo")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El nombre solo puede contener letras")
    private String nombre;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 100, message = "La dirección es demasiado larga")
    private String direccion;

    private String mapa;

    @Min(value = 1, message = "El tiempo para la exumacion no puede ser negativo")
    private int tiempoExhumacion;

    private AyuntamientoReducidoDTO ayuntamiento;

    // private Set<ZonaDTO> zonas;

}
