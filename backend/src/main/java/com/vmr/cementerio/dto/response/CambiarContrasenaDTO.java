package com.vmr.cementerio.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CambiarContrasenaDTO {
    
    @NotBlank(message = "La contraseña no puede estar vacia")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String contrasenaActual;
    
    @NotBlank(message = "La contraseña no puede estar vacia")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String contrasena;

}
