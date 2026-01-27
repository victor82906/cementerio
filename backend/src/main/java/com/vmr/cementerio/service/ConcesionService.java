package com.vmr.cementerio.service;

import com.vmr.cementerio.dto.response.ConcesionDTO;
import java.util.List;

public interface ConcesionService {
    
    public List<ConcesionDTO> getAll();

    public ConcesionDTO findById(Long id);

    public ConcesionDTO save(Long parcelaId, ConcesionDTO concesionDTO);

    public ConcesionDTO update(Long id, ConcesionDTO concesionDTO);

    public void delete(Long id);

    public ConcesionDTO findByParcelaId(Long id);
    
    public List<ConcesionDTO> findByCiudadanoId(Long id);

}
