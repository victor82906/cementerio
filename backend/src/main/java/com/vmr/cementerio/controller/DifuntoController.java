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
import com.vmr.cementerio.dto.response.DifuntoDTO;
import com.vmr.cementerio.service.DifuntoService;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/difunto")
public class DifuntoController {
    
    private final DifuntoService difuntoService;

    @GetMapping
    public ResponseEntity<List<DifuntoDTO>> getAll(){
        return ResponseEntity.ok(difuntoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DifuntoDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(difuntoService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DifuntoDTO> update(@PathVariable Long id, @Valid @RequestBody DifuntoDTO difuntoDTO){
        return ResponseEntity.ok(difuntoService.update(id, difuntoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        difuntoService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
