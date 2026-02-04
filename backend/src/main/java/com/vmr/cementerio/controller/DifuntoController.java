package com.vmr.cementerio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.vmr.cementerio.dto.response.DifuntoDTO;
import com.vmr.cementerio.dto.response.FacturaDTO;
import com.vmr.cementerio.dto.response.ParcelaDTO;
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
    @PreAuthorize("hasRole('ADMIN') or (hasRole('CIUDADANO') and @difuntoRepository.existsByIdAndParcelaConcesionCiudadanoId(#id, principal.id))")
    public ResponseEntity<DifuntoDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(difuntoService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DifuntoDTO> update(@PathVariable Long id, @Valid @RequestBody DifuntoDTO difuntoDTO){
        return ResponseEntity.ok(difuntoService.update(id, difuntoDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('CIUDADANO') and @difuntoRepository.existsByIdAndParcelaConcesionCiudadanoId(#id, principal.id))")
    public ResponseEntity<FacturaDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(difuntoService.delete(id));
    }

    @GetMapping("/{id}/parcela")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('CIUDADANO') and @difuntoRepository.existsByIdAndParcelaConcesionCiudadanoId(#id, principal.id))")
    public ResponseEntity<ParcelaDTO> getParcela(@PathVariable Long id){
        return ResponseEntity.ok(difuntoService.getParcelaByDifuntoId(id));
    }

}
