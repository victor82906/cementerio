package com.vmr.cementerio.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import jakarta.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Concesion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double precio;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    @OneToOne
    @JoinColumn(name = "parcela_id")
    private Parcela parcela;

    @ManyToOne
    @JoinColumn(name = "ciudadano_id")
    private Ciudadano ciudadano;


}
