package com.vmr.cementerio.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Email no valido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    private String contrasena;
}
