package com.vmr.cementerio.service;

import com.vmr.cementerio.dto.response.ParcelaDTO;
import java.util.List;

public interface ParcelaService {
    
    public List<ParcelaDTO> getAll();

    public ParcelaDTO findById(Long id);

    public ParcelaDTO save(Long zonaId, ParcelaDTO parcelaDTO);

    public ParcelaDTO update(Long id, ParcelaDTO parcelaDTO);

    public void delete(Long id);

    public List<ParcelaDTO> findByZonaId(Long id);

}
