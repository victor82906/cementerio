package com.vmr.cementerio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ciudadano extends Usuario {
    
    @Column(unique = true, nullable = false)
    private String dni;
    private String apellidos;
    
    @ManyToMany(mappedBy = "ciudadanos")
    private Set<Ayuntamiento> ayuntamientos = new HashSet<>();

    @OneToMany(mappedBy = "ciudadano", fetch = FetchType.EAGER)
    private Set<Concesion> concesiones = new HashSet<>();

}
