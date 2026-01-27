package com.vmr.cementerio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.vmr.cementerio.dto.response.ConcesionDTO;
import com.vmr.cementerio.service.ConcesionService;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/concesion")
public class ConcesionController {
    
    private ConcesionService concesionService;

    @GetMapping
    public ResponseEntity<List<ConcesionDTO>> getAll(){
        return ResponseEntity.ok(concesionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConcesionDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(concesionService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConcesionDTO> update(@PathVariable Long id, @Valid @RequestBody ConcesionDTO concesionDTO){
        return ResponseEntity.ok(concesionService.update(id, concesionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        concesionService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
