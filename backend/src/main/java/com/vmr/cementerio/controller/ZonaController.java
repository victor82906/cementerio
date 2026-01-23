package com.vmr.cementerio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vmr.cementerio.service.ZonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.vmr.cementerio.dto.response.ZonaDTO;
import jakarta.validation.Valid;
import java.util.List;
import com.vmr.cementerio.dto.response.ParcelaDTO;
import com.vmr.cementerio.service.ParcelaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/zona")
public class ZonaController {
    
    private final ZonaService zonaService;
    private final ParcelaService parcelaService;

    @GetMapping
    public ResponseEntity<List<ZonaDTO>> getAll(){
        return ResponseEntity.ok(zonaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZonaDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(zonaService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZonaDTO> update(@PathVariable Long id, @Valid @RequestBody ZonaDTO zonaDTO){
        return ResponseEntity.ok(zonaService.update(id, zonaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        zonaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/parcela")
    public ResponseEntity<List<ParcelaDTO>> getParcelas(@PathVariable Long id){
        return ResponseEntity.ok(parcelaService.findByZonaId(id));
    }

    @PostMapping("/{id}/parcela")
    public ResponseEntity<ParcelaDTO> saveParcela(@PathVariable Long id, @Valid @RequestBody ParcelaDTO parcelaDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(parcelaService.save(id, parcelaDTO));
    }

}
