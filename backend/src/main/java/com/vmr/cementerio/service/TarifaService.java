package com.vmr.cementerio.service;

import com.vmr.cementerio.dto.response.TarifaDTO;
import java.util.List;

public interface TarifaService {
    
    public List<TarifaDTO> getAll();

    public TarifaDTO findById(Long id);

    public TarifaDTO save(Long cementerioId, TarifaDTO tarifaDTO);

    public TarifaDTO update(Long id, TarifaDTO tarifaDTO);

    public void delete(Long id);

    public List<TarifaDTO> findByCementerioId(Long id);


}
