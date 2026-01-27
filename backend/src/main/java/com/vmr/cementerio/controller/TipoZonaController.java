package com.vmr.cementerio.controller;

import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.vmr.cementerio.dto.response.TipoZonaDTO;
import com.vmr.cementerio.service.TipoZonaService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tipo-zona")
public class TipoZonaController {
    
    private final TipoZonaService tipoZonaService;

    @GetMapping
    public ResponseEntity<List<TipoZonaDTO>> getAll(){
        return ResponseEntity.ok(tipoZonaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoZonaDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(tipoZonaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TipoZonaDTO> save(@Valid @RequestBody TipoZonaDTO tipoZonaDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoZonaService.save(tipoZonaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoZonaDTO> update(@PathVariable Long id, @Valid @RequestBody TipoZonaDTO tipoZonaDTO){
        return ResponseEntity.ok(tipoZonaService.update(id, tipoZonaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        tipoZonaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
