package com.vmr.cementerio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Difunto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El DNI es obligatorio")
    @Pattern(regexp = "^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$", message = "El formato del DNI no es válido")
    private String dni;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre es demasiado largo")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El nombre solo puede contener letras")
    private String nombre;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 70, message = "Los apellidos son demasiado largos")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El apellido solo puede contener letras")
    private String apellidos;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser anterior a hoy")
    private LocalDate fechaNacimiento;

    @NotNull(message = "La fecha de fallecimiento es obligatoria")
    @Past(message = "La fecha de fallecimiento debe ser anterior a hoy")
    private LocalDate fechaFallecimiento;

    @NotNull(message = "La fecha de entierro es obligatoria")
    @PastOrPresent(message = "La fecha de entierro debe ser hoy o anterior")
    private LocalDate fechaEntierro;

    private String foto;

    @Size(max = 300, message = "La biografia es demasiado larga")
    private String biografia;

    @ManyToOne
    @JoinColumn(name = "parcela_id")
    private Parcela parcela;

}
