package com.vmr.cementerio.service;

import com.vmr.cementerio.dto.response.CementerioDTO;
import com.vmr.cementerio.dto.response.ZonaDTO;
import java.util.List;

public interface ZonaService {
    
    public List<ZonaDTO> getAll();

    public ZonaDTO findById(Long id);

    public ZonaDTO save(Long cementerioId, ZonaDTO zonaDTO);

    public ZonaDTO update(Long id, ZonaDTO zonaDTO);

    public void delete(Long id);

    public List<ZonaDTO> findByCementerioId(Long id);

    public CementerioDTO findCementerioByZonaId(Long id);

}
