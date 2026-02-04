package com.vmr.cementerio.controller;

import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import java.util.List;
import com.vmr.cementerio.dto.response.CiudadanoDTO;
import com.vmr.cementerio.dto.response.CiudadanoEditDTO;
import org.springframework.web.bind.annotation.RequestBody;
import com.vmr.cementerio.dto.response.ConcesionDTO;
import com.vmr.cementerio.service.CiudadanoService;
import com.vmr.cementerio.service.ConcesionService;
import com.vmr.cementerio.dto.response.DifuntoDTO;
import com.vmr.cementerio.service.DifuntoService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ciudadano")
public class CiudadanoController {
    
    private final CiudadanoService ciudadanoService;
    private final ConcesionService concesionService;
    private final DifuntoService difuntoService;

    @GetMapping
    public ResponseEntity<List<CiudadanoDTO>> getAll(@RequestParam(required = false) String nombre){
        if(nombre == null || nombre.isBlank()){
            return ResponseEntity.ok(ciudadanoService.getAll());
        }else{
            return ResponseEntity.ok(ciudadanoService.buscaPorNombre(nombre));
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('CIUDADANO') and #id == principal.id)")
    public ResponseEntity<CiudadanoDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(ciudadanoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CiudadanoDTO> save(@Valid @RequestBody CiudadanoDTO ciudadanoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(ciudadanoService.save(ciudadanoDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('CIUDADANO') and #id == principal.id)")
    public ResponseEntity<CiudadanoEditDTO> update(@PathVariable Long id, @Valid @RequestBody CiudadanoEditDTO ciudadanoDTO){
        return ResponseEntity.ok(ciudadanoService.update(id, ciudadanoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        ciudadanoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/concesion")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('CIUDADANO') and #id == principal.id)")
    public ResponseEntity<List<ConcesionDTO>> getConcesion(@PathVariable Long id){
        return ResponseEntity.ok(concesionService.findByCiudadanoId(id));
    }

    @GetMapping("/{id}/difunto")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('CIUDADANO') and #id == principal.id)")
    public ResponseEntity<List<DifuntoDTO>> getDifuntos(@PathVariable Long id){
        return ResponseEntity.ok(difuntoService.findDifuntosByCiudadanoId(id));
    }

}
