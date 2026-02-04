package com.vmr.cementerio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.vmr.cementerio.dto.response.AyuntamientoDTO;
import java.util.List;
import jakarta.validation.Valid;
import com.vmr.cementerio.dto.response.CementerioDTO;
import com.vmr.cementerio.dto.response.ServicioDTO;
import com.vmr.cementerio.service.AyuntamientoService;
import com.vmr.cementerio.service.CementerioService;
import com.vmr.cementerio.dto.response.AyuntamientoEditDTO;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import com.vmr.cementerio.service.ServicioService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/ayuntamiento")
public class AyuntamientoController {
    
    private final AyuntamientoService ayuntamientoService;
    private final CementerioService cementerioService;
    private final ServicioService servicioService;

    @GetMapping
    public ResponseEntity<List<AyuntamientoDTO>> getAll(@RequestParam(required = false) String nombre){
        if(nombre == null || nombre.isBlank()){
            return ResponseEntity.ok(ayuntamientoService.getAll());
        }else{
            return ResponseEntity.ok(ayuntamientoService.buscaPorNombre(nombre));
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('AYUNTAMIENTO') and #id == principal.id)")
    public ResponseEntity<AyuntamientoDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(ayuntamientoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AyuntamientoDTO> save(@Valid @RequestBody AyuntamientoDTO ayuntamientoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(ayuntamientoService.save(ayuntamientoDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('AYUNTAMIENTO') and #id == principal.id)")
    public ResponseEntity<AyuntamientoEditDTO> update(@PathVariable Long id, @Valid @RequestBody AyuntamientoEditDTO ayuntamientoDTO){
        return ResponseEntity.ok(ayuntamientoService.update(id, ayuntamientoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        ayuntamientoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/cementerio")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('AYUNTAMIENTO') and #id == principal.id)")
    public ResponseEntity<List<CementerioDTO>> getCementerios(@PathVariable Long id){
        return ResponseEntity.ok(cementerioService.findByAyuntamientoId(id));
    }
    
    @PostMapping("/{id}/cementerio")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('AYUNTAMIENTO') and #id == principal.id)")
    public ResponseEntity<CementerioDTO> saveCementerio(@PathVariable Long id, 
                                                        @RequestPart("datos") @Valid CementerioDTO cementerioDTO,
                                                        @RequestPart("foto") MultipartFile foto) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(cementerioService.save(id, cementerioDTO, foto));
    }

    @GetMapping("/{id}/servicio")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('AYUNTAMIENTO') and #id == principal.id)")
    public ResponseEntity<List<ServicioDTO>> getServicios(@PathVariable Long id){
        return ResponseEntity.ok(servicioService.getServiciosByAyuntamientoId(id));
    }

}
