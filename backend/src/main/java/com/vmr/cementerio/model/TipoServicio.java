package com.vmr.cementerio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Column;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoServicio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    private String descripcion;

    @OneToMany(mappedBy = "tipoServicio")
    private Set<Servicio> servicios = new HashSet<>();

    @OneToMany(mappedBy = "tipoServicio")
    private Set<Tarifa> tarifas = new HashSet<>();


}
