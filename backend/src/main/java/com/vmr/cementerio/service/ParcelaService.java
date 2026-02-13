package com.vmr.cementerio.service;

import com.vmr.cementerio.dto.response.AyuntamientoDTO;
import com.vmr.cementerio.dto.response.ParcelaDTO;
import java.util.List;
import com.vmr.cementerio.model.Zona;
import com.vmr.cementerio.dto.response.ZonaDTO;

public interface ParcelaService {
    
    public List<ParcelaDTO> getAll();

    public ParcelaDTO findById(Long id);

    public ParcelaDTO save(Long zonaId, ParcelaDTO parcelaDTO);

    public ParcelaDTO update(Long id, ParcelaDTO parcelaDTO);

    public void delete(Long id);

    public List<ParcelaDTO> findByZonaId(Long id);

    public void generarParcelasParaZona(Zona zona);

    public AyuntamientoDTO findAyuntamientoByParcelaId(Long parcelaId);

    public ZonaDTO findZonaByParcelaId(Long parcelaId);

    public List<ParcelaDTO> findParcelasLibresByZonaId(Long id);

} 
