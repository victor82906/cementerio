package com.vmr.cementerio.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.vmr.cementerio.dto.response.TarifaDTO;
import com.vmr.cementerio.service.TarifaService;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tarifa")
public class TarifaController {
    
    private final TarifaService tarifaService;

    @GetMapping
    public ResponseEntity<List<TarifaDTO>> getAll(){
        return ResponseEntity.ok(tarifaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarifaDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(tarifaService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarifaDTO> update(@PathVariable Long id, @Valid @RequestBody TarifaDTO tarifaDTO){
        return ResponseEntity.ok(tarifaService.update(id, tarifaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        tarifaService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
