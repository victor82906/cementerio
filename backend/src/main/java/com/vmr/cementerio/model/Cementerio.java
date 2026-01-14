package com.vmr.cementerio.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cementerio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String direccion;

    private String mapa;

    private int tiempoExumacion;

    @ManyToOne
    @JoinColumn(name = "ayuntamiento_id")
    private Ayuntamiento ayuntamiento;

    @OneToMany(mappedBy = "cementerio")
    private Set<Zona> zonas = new HashSet<>();


}
