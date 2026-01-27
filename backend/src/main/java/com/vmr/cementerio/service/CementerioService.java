package com.vmr.cementerio.service;

import com.vmr.cementerio.dto.response.CementerioDTO;
import java.util.List;

public interface CementerioService {

    public List<CementerioDTO> getAll();

    public List<CementerioDTO> buscaPorNombre(String nombre);

    public CementerioDTO findById(Long id);

    public CementerioDTO save(Long ayuntamientoId, CementerioDTO cementerioDTO);

    public CementerioDTO update(Long id, CementerioDTO cementerioDTO);

    public void delete(Long id);

    public List<CementerioDTO> findByAyuntamientoId(Long id);

}
