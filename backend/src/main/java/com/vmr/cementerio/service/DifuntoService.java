package com.vmr.cementerio.service;

import com.vmr.cementerio.dto.response.DifuntoDTO;
import java.util.List;

public interface DifuntoService {
    
    public List<DifuntoDTO> getAll();

    public DifuntoDTO findById(Long id);

    public DifuntoDTO save(Long parcelaId, DifuntoDTO difuntoDTO);

    public DifuntoDTO update(Long id, DifuntoDTO difuntoDTO);

    public void delete(Long id);

    public List<DifuntoDTO> findByParcelaId(Long id);

    // public List<DifuntoDTO> findByCiudadanoId(Long id);

}
