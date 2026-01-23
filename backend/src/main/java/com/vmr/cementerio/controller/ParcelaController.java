package com.vmr.cementerio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.vmr.cementerio.dto.response.ParcelaDTO;
import jakarta.validation.Valid;
import java.util.List;
import com.vmr.cementerio.service.ParcelaService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/zona")
public class ParcelaController {
    
    private final ParcelaService parcelaService;

    @GetMapping
    public ResponseEntity<List<ParcelaDTO>> getAll(){
        return ResponseEntity.ok(parcelaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParcelaDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(parcelaService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParcelaDTO> update(@PathVariable Long id, @Valid @RequestBody ParcelaDTO parcelaDTO){
        return ResponseEntity.ok(parcelaService.update(id, parcelaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        parcelaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}