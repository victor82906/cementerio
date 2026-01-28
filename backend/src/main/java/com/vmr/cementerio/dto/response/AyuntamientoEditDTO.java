package com.vmr.cementerio.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AyuntamientoEditDTO {

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe proporcionar un formato de email válido")
    private String email;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre es demasiado largo")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El nombre solo puede contener letras")
    private String nombre;

    @NotBlank(message = "El telefono es obligatorio")
    @Pattern(regexp = "^[679][0-9]{8}$", message = "Formato de teléfono no válido")
    private String telefono;

    @Size(max = 50, message = "La dirección es demasiado larga")
    private String direccion;

    @NotBlank(message = "El código es obligatorio")
    private String codigo;
}
