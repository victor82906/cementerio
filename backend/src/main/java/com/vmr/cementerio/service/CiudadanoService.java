package com.vmr.cementerio.service;

import com.vmr.cementerio.dto.response.CiudadanoDTO;
import java.util.List;

public interface CiudadanoService {
    
    public List<CiudadanoDTO> getAll();

    public List<CiudadanoDTO> buscaPorNombre(String nombre);

    public CiudadanoDTO findById(Long id);

    public CiudadanoDTO save(CiudadanoDTO ciudadanoDTO);

    public CiudadanoDTO update(Long id, CiudadanoDTO ciudadanoDTO);

    public void delete(Long id);

}
