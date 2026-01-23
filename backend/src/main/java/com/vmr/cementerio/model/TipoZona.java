package com.vmr.cementerio.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.List;
import com.vmr.cementerio.enums.TipoParcela;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoZona {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private TipoParcela nombre;

    @OneToMany(mappedBy = "tipoZona")
    private List<Zona> zonas;


}
