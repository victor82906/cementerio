package com.vmr.cementerio.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.vmr.cementerio.dto.response.TipoServicioDTO;
import com.vmr.cementerio.service.TipoServicioService;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tipo-servicio")
public class TipoServicioController {
    
    private final TipoServicioService tipoServicioService;

    @GetMapping
    public ResponseEntity<List<TipoServicioDTO>> getAll(){
        return ResponseEntity.ok(tipoServicioService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoServicioDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(tipoServicioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TipoServicioDTO> save(@Valid @RequestBody TipoServicioDTO tipoServicioDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoServicioService.save(tipoServicioDTO));
    }

    @PutMapping
    public ResponseEntity<TipoServicioDTO> update(@PathVariable Long id, @Valid @RequestBody TipoServicioDTO tipoServicioDTO){
        return ResponseEntity.ok(tipoServicioService.update(id, tipoServicioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        tipoServicioService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
