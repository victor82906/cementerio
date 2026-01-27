package com.vmr.cementerio.service;

import com.vmr.cementerio.dto.response.ServicioDTO;
import java.util.List;

public interface ServicioService {
    
    public List<ServicioDTO> getAll();

    public ServicioDTO findById(Long id);

    public ServicioDTO save(Long parcelaId, ServicioDTO servicioDTO);

    public ServicioDTO update(Long id, ServicioDTO servicioDTO);

    public void delete(Long id);

    public List<ServicioDTO> findByParcelaId(Long id);

}
