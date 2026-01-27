package com.vmr.cementerio.controller;

import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import java.util.List;
import com.vmr.cementerio.dto.response.CementerioDTO;
import com.vmr.cementerio.dto.response.TarifaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import com.vmr.cementerio.dto.response.ZonaDTO;
import com.vmr.cementerio.service.CementerioService;
import com.vmr.cementerio.service.TarifaService;
import com.vmr.cementerio.service.ZonaService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/cementerio")
public class CementerioController {
    
    private final CementerioService cementerioService;
    private final TarifaService tarifaService;
    private final ZonaService zonaService;

    @GetMapping
    public ResponseEntity<List<CementerioDTO>> getAll(@RequestParam(required = false) String nombre){
        if(nombre == null || nombre.isBlank()){
            return ResponseEntity.ok(cementerioService.getAll());
        }else{
            return ResponseEntity.ok(cementerioService.buscaPorNombre(nombre));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CementerioDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(cementerioService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CementerioDTO> update(@PathVariable Long id, @Valid @RequestBody CementerioDTO cementerioDTO){
        return ResponseEntity.ok(cementerioService.update(id, cementerioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        cementerioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/tarifa")
    public ResponseEntity<List<TarifaDTO>> getTarifas(@PathVariable Long id){
        return ResponseEntity.ok(tarifaService.findByCementerioId(id));          
    }

    @PostMapping("/{id}/tarifa")
    public ResponseEntity<TarifaDTO> saveTarifa(@PathVariable Long id, @Valid @RequestBody TarifaDTO tarifaDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(tarifaService.save(id, tarifaDTO));
    }

    @GetMapping("/{id}/zona")
    public ResponseEntity<List<ZonaDTO>> getZonas(@PathVariable Long id){
        return ResponseEntity.ok(zonaService.findByCementerioId(id));          
    }

    @PostMapping("/{id}/zona")
    public ResponseEntity<ZonaDTO> saveZona(@PathVariable Long id, @Valid @RequestBody ZonaDTO zonaDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(zonaService.save(id, zonaDTO));
    }




}
