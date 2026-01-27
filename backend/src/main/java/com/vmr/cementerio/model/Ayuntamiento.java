package com.vmr.cementerio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "ayuntamiento", fetch = FetchType.LAZY)
    private Set<Cementerio> cementerios = new HashSet<>();

}
