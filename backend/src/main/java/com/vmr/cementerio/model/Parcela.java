package com.vmr.cementerio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Parcela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    private int capacidad;

    private boolean ocupada;

    @ManyToOne
    @JoinColumn(name = "zona_id")
    private Zona zona;

    @OneToMany(mappedBy = "parcela", fetch = FetchType.EAGER)
    private Set<Difunto> difuntos = new HashSet<>();

    @OneToOne(mappedBy = "parcela")
    private Concesion concesion;

    @OneToMany(mappedBy = "parcela", fetch = FetchType.LAZY)
    private Set<Servicio> servicios = new HashSet<>();

}
