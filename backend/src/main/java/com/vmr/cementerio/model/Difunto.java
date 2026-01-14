package com.vmr.cementerio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Difunto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dni;

    private String nombre;

    private String apellidos;

    private LocalDate fechaFallecimiento;

    private LocalDate fechaEntierro;

    private String foto;

    private String biografia;

    @ManyToOne
    @JoinColumn(name = "parcela_id")
    private Parcela parcela;

}
