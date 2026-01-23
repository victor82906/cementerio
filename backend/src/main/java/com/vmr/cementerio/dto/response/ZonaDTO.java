package com.vmr.cementerio.dto.response;

import java.util.Set;
import com.vmr.cementerio.model.Parcela;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ZonaDTO {

    private Long id;
    
    @Min(value = 1, message = "El numero de filas tiene que ser de 1 o superior")
    private int filas;

    @Min(value = 1, message = "El numero de columnas tiene que ser de 1 o superior")
    private int columnas;

    @NotBlank(message = "Las coordenadas son obligatorias")
    private String coordenadas;

    private TipoZonaDTO tipoZona;

    private Set<Parcela> parcelas;
    
}
