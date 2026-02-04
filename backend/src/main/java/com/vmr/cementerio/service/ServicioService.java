package com.vmr.cementerio.service;

import com.vmr.cementerio.dto.response.ServicioDTO;
import java.util.List;
import com.vmr.cementerio.dto.response.FacturaDTO;

public interface ServicioService {
    
    public List<ServicioDTO> getAll();

    public ServicioDTO findById(Long id);

    public FacturaDTO save(Long parcelaId, ServicioDTO servicioDTO);

    public ServicioDTO update(Long id, ServicioDTO servicioDTO);

    public void delete(Long id);

    public List<ServicioDTO> findByParcelaId(Long id);

    public List<ServicioDTO> getServiciosByAyuntamientoId(Long id);

    public ServicioDTO updateRealizado(Long id);

}
