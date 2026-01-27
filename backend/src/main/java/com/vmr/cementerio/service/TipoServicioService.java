package com.vmr.cementerio.service;

import com.vmr.cementerio.dto.response.TipoServicioDTO;
import java.util.List;

public interface TipoServicioService {
    
    public List<TipoServicioDTO> getAll();

    public TipoServicioDTO findById(Long id);

    public TipoServicioDTO save(TipoServicioDTO tipoServicioDTO);

    public TipoServicioDTO update(Long id, TipoServicioDTO tipoServicioDTO);

    public void delete(Long id);

}
