package com.vmr.cementerio.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
    
    private double importe;

    private LocalDate fecha;

    private String nombre;

    private String apellidos;
    
    private String telefono;

    private String codigoAyto;

    private String nombreAyto;

    private String concepto;

    @OneToOne(mappedBy = "factura")
    private Servicio servicio;

    @OneToOne(mappedBy = "factura")
    private Concesion concesion;

    @OneToOne(mappedBy = "factura")
    private Pago pago;

}
