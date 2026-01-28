package com.vmr.cementerio.service;

import com.vmr.cementerio.dto.response.CiudadanoDTO;
import java.util.List;
import com.vmr.cementerio.dto.response.CiudadanoEditDTO;

public interface CiudadanoService {
    
    public List<CiudadanoDTO> getAll();

    public List<CiudadanoDTO> buscaPorNombre(String nombre);

    public CiudadanoDTO findById(Long id);

    public CiudadanoDTO save(CiudadanoDTO ciudadanoDTO);

    public CiudadanoEditDTO update(Long id, CiudadanoEditDTO ciudadanoDTO);

    public void delete(Long id);

}
