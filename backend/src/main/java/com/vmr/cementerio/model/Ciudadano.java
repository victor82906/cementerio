package com.vmr.cementerio.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.Column;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ciudadano extends Usuario {
    
    @Column(unique = true)
    private String dni;

    private String apellidos;

    @OneToMany(mappedBy = "ciudadano", fetch = FetchType.EAGER)
    private Set<Concesion> concesiones = new HashSet<>();

}
