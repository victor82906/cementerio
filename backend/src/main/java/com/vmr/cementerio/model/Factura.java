package com.vmr.cementerio.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Factura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @PositiveOrZero(message = "El importe no puede ser negativo")
    private double importe;

    @NotNull(message = "La fecha es obligatoria")
    @PastOrPresent(message = "La fecha no debe ser hoy o anterior")
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

    private boolean pagado;

    @NotBlank(message = "El codigo del ayuntamiento es obligatorio")
    private String codigoAyto;

    @NotBlank(message = "El nombre del ayuntamiento es obligatorio")
    @Size(max = 50, message = "El nombre es demasiado largo")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El nombre solo puede contener letras")
    private String nombreAyto;

    @NotBlank(message = "El concepto es obligatorio")
    @Size(max = 100, message = "El concepto es demasiado largo")
    private String concepto;

    @OneToOne(mappedBy = "factura")
    private Servicio servicio;

    @OneToOne(mappedBy = "factura")
    private Concesion concesion;

}
