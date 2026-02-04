package com.vmr.cementerio.dto.response;

import java.time.LocalDate;
import lombok.Data;

@Data
public class ServicioDTO {
    
    private Long id;

    private LocalDate fecha;

    private boolean realizado;

    private TipoServicioDTO tipoServicio;

    private ParcelaDTO parcela;

}
