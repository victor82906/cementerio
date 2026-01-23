package com.vmr.cementerio.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CiudadanoDTO {

    private Long id;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe proporcionar un formato de email válido")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacia")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String contrasena;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre es demasiado largo")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El nombre solo puede contener letras")
    private String nombre;

    // @NotBlank(message = "El telefono es obligatorio")
    @Pattern(regexp = "^[679][0-9]{8}$", message = "Formato de teléfono no válido")
    private String telefono;

    @Size(max = 100, message = "La dirección es demasiado larga")
    private String direccion;

    private String foto;

    // @NotBlank(message = "El DNI es obligatorio")
    @Pattern(regexp = "^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$", message = "El formato del DNI no es válido")
    private String dni;

    // @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 70, message = "Los apellidos son demasiado largos")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El apellido solo puede contener letras")
    private String apellidos;

}
