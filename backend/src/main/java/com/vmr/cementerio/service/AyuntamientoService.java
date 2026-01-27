package com.vmr.cementerio.service;

import java.util.List;
import com.vmr.cementerio.dto.response.AyuntamientoDTO;
import com.vmr.cementerio.model.Ayuntamiento;

public interface AyuntamientoService {

    public List<AyuntamientoDTO> getAll();

    public List<AyuntamientoDTO> buscaPorNombre(String nombre);

    public AyuntamientoDTO findById(Long id);

    public AyuntamientoDTO save(AyuntamientoDTO ayuntamientoDTO);

    public AyuntamientoDTO update(Long id, AyuntamientoDTO ayuntamientoDTO);

    public void delete(Long id);

    public Ayuntamiento findByEmail(String email);

}
