package com.vmr.cementerio.service;

import com.vmr.cementerio.dto.response.DifuntoDTO;
import com.vmr.cementerio.dto.response.FacturaDTO;
import java.util.List;
import com.vmr.cementerio.dto.response.ParcelaDTO;

public interface DifuntoService {
    
    public List<DifuntoDTO> getAll();

    public DifuntoDTO findById(Long id);

    public FacturaDTO save(Long parcelaId, DifuntoDTO difuntoDTO);

    public DifuntoDTO update(Long id, DifuntoDTO difuntoDTO);

    public FacturaDTO delete(Long id);

    public List<DifuntoDTO> findByParcelaId(Long id);

    public List<DifuntoDTO> findDifuntosByCiudadanoId(Long id);

    public ParcelaDTO getParcelaByDifuntoId(Long id);

}
