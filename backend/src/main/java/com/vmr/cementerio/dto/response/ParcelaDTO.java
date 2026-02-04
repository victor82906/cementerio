package com.vmr.cementerio.dto.response;

import java.util.Set;
import com.vmr.cementerio.model.Concesion;
import com.vmr.cementerio.model.Difunto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ParcelaDTO {
    
    private Long id;

    @NotBlank(message = "El numero de parcela es obligatorio")
    private String numero;

    private boolean ocupada;

    // private Set<DifuntoDTO> difuntos;

}
