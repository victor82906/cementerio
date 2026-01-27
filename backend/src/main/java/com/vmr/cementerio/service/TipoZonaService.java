package com.vmr.cementerio.service;

import com.vmr.cementerio.dto.response.TipoZonaDTO;
import java.util.List;

public interface TipoZonaService {
    
    public List<TipoZonaDTO> getAll();

    public TipoZonaDTO findById(Long id);

    public TipoZonaDTO save(TipoZonaDTO tipoZonaDTO);

    public TipoZonaDTO update(Long id, TipoZonaDTO tipoZonaDTO);

    public void delete(Long id);

}
