package com.vmr.cementerio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinTable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ayuntamiento extends Usuario {
    
    @Column(unique = true)
    private String codigo;

    @ManyToMany
    @JoinTable(name = "ciudadano_id", // revisar si aqui hay que ponerlo asi o usuario_id
    joinColumns = @JoinColumn(name = "ayuntamiento_id"), 
    inverseJoinColumns = @JoinColumn(name = "ciudadano_id"))
    private Set<Ciudadano> ciudadanos = new HashSet<>();

    @OneToMany(mappedBy = "ayuntamiento", fetch = FetchType.LAZY)
    private Set<Cementerio> cementerios = new HashSet<>();

}
