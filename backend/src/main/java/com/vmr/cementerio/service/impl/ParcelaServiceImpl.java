package com.vmr.cementerio.service.impl;

import org.springframework.stereotype.Service;
import com.vmr.cementerio.repository.ParcelaRepository;
import lombok.RequiredArgsConstructor;
import com.vmr.cementerio.mapper.ParcelaMapper;
import com.vmr.cementerio.model.Parcela;
import com.vmr.cementerio.dto.response.ParcelaDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;
import com.vmr.cementerio.model.Zona;
import com.vmr.cementerio.repository.ZonaRepository;
import com.vmr.cementerio.service.ParcelaService;
import com.vmr.cementerio.dto.response.AyuntamientoDTO;
import com.vmr.cementerio.mapper.AyuntamientoMapper;
import com.vmr.cementerio.dto.response.ZonaDTO;
import com.vmr.cementerio.mapper.ZonaMapper;


@Service
@RequiredArgsConstructor
public class ParcelaServiceImpl implements ParcelaService{
    
    private final ParcelaRepository parcelaRepository;
    private final ParcelaMapper parcelaMapper;
    private final ZonaRepository zonaRepository;
    private final AyuntamientoMapper ayuntamientoMapper;
    private final ZonaMapper zonaMapper;

    public List<ParcelaDTO> getAll(){
        return parcelaRepository.findAll()
                .stream()
                .map(parcelaMapper::toDTO)
                .toList();
    }

    public ParcelaDTO findById(Long id){
        return parcelaMapper.toDTO(parcelaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Parcela no encontrada")));
    }

    @Transactional
    public ParcelaDTO save(Long zonaId, ParcelaDTO parcelaDTO){
        Zona zona = zonaRepository.findById(zonaId)
                .orElseThrow(() -> new EntityNotFoundException("Zona no encontrada"));

        Parcela parcela = parcelaMapper.toEntity(parcelaDTO);
        parcela.setZona(zona);
        return parcelaMapper.toDTO(parcelaRepository.save(parcela));
    }

    @Transactional
    public ParcelaDTO update(Long id, ParcelaDTO parcelaDTO){
        Parcela parcela = parcelaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Parcela no encontrada"));
        
        parcela = parcelaMapper.toEntity(parcelaDTO);
        return parcelaMapper.toDTO(parcelaRepository.save(parcela));
    }

    @Transactional
    public void delete(Long id){
        if(parcelaRepository.existsById(id)){
            parcelaRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Parcela no encontrada");
        }
    }

    public List<ParcelaDTO> findByZonaId(Long id){
        return parcelaRepository.findByZonaId(id)
                .stream()
                .map(parcelaMapper::toDTO)
                .toList();
    }

    @Transactional
    public void generarParcelasParaZona(Zona zona){
        int filas = zona.getFilas();
        int columnas = zona.getColumnas();
        for(int fila = 1; fila <= filas; fila++){
            for(int columna = 1; columna <= columnas; columna++){
                Parcela parcela = new Parcela();
                parcela.setZona(zona);
                parcela.setNumero(columna + "," + fila);
                parcela.setOcupada(false);
                parcelaRepository.save(parcela);
            }
        }
    }

    public AyuntamientoDTO findAyuntamientoByParcelaId(Long parcelaId){
        Parcela parcela = parcelaRepository.findById(parcelaId)
                .orElseThrow(() -> new EntityNotFoundException("Parcela no encontrada"));

        return ayuntamientoMapper.toDTO(parcela.getZona().getCementerio().getAyuntamiento());
    }

    public ZonaDTO findZonaByParcelaId(Long parcelaId){
        Parcela parcela = parcelaRepository.findById(parcelaId)
                .orElseThrow(() -> new EntityNotFoundException("Parcela no encontrada"));

        return zonaMapper.toDTO(parcela.getZona());
    }

}
