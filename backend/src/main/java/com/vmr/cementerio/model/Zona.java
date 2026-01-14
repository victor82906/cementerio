package com.vmr.cementerio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.FetchType;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Zona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private int filas;

    private int columnas;

    private String coordenadas;

    @ManyToOne
    @JoinColumn(name = "cementerio_id")
    private Cementerio cementerio;

    @ManyToOne
    @JoinColumn(name = "tipo_zona_id")
    private TipoZona tipoZona;

    @OneToMany(mappedBy = "zona", fetch = FetchType.LAZY)
    private Set<Parcela> parcelas = new HashSet<>();


}
