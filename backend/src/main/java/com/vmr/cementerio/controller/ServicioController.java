package com.vmr.cementerio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.vmr.cementerio.dto.response.ServicioDTO;
import com.vmr.cementerio.service.ServicioService;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/servicio")
public class ServicioController {
    
    private final ServicioService servicioService;

    @GetMapping
    public ResponseEntity<List<ServicioDTO>> getAll(){
        return ResponseEntity.ok(servicioService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or " +
                  "(hasRole('CIUDADANO') and @servicioRepository.existsByIdAndParcelaConcesionCiudadanoId(#id, principal.id)) or " +
                  "(hasRole('AYUNTAMIENTO') and @servicioRepository.existsByIdAndParcelaZonaCementerioAyuntamientoId(#id, principal.id))")
    public ResponseEntity<ServicioDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(servicioService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicioDTO> update(@PathVariable Long id, @Valid @RequestBody ServicioDTO servicioDTO){
        return ResponseEntity.ok(servicioService.update(id, servicioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        servicioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/realizado")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('AYUNTAMIENTO') and @servicioRepository.existsByIdAndParcelaZonaCementerioAyuntamientoId(#id, principal.id))")
    public ResponseEntity<ServicioDTO> updateRealizado(@PathVariable Long id){
        return ResponseEntity.ok(servicioService.updateRealizado(id));
    }


}
