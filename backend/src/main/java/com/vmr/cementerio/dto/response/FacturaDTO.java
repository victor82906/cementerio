package com.vmr.cementerio.dto.response;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FacturaDTO {
    
    private Long id;
    
    @PositiveOrZero(message = "El importe no puede ser negativo")
    private double importe;

    private LocalDate fecha;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre es demasiado largo")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El nombre solo puede contener letras")
    private String nombre;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 70, message = "Los apellidos son demasiado largos")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El apellido solo puede contener letras")
    private String apellidos;
    
    @NotBlank(message = "El telefono es obligatorio")
    @Pattern(regexp = "^[679][0-9]{8}$", message = "Formato de teléfono no válido")
    private String telefono;

    @NotBlank(message = "El codigo del ayuntamiento es obligatorio")
    private String codigoAyto;

    @NotBlank(message = "El nombre del ayuntamiento es obligatorio")
    @Size(max = 50, message = "El nombre es demasiado largo")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El nombre solo puede contener letras")
    private String nombreAyto;

    @NotBlank(message = "El concepto es obligatorio")
    @Size(max = 100, message = "El concepto es demasiado largo")
    private String concepto;

    private ServicioDTO servicio;

    private ConcesionDTO concesion;

}
