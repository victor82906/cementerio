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
import com.vmr.cementerio.dto.response.ParcelaDTO;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import com.vmr.cementerio.dto.response.ServicioDTO;
import com.vmr.cementerio.service.ConcesionService;
import com.vmr.cementerio.service.DifuntoService;
import com.vmr.cementerio.service.ParcelaService;
import com.vmr.cementerio.service.ServicioService;
import com.vmr.cementerio.dto.response.ConcesionDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parcela")
public class ParcelaController {
    
    private final ParcelaService parcelaService;
    private final DifuntoService difuntoService;
    private final ServicioService servicioService;
    private final ConcesionService concesionService;

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

    @GetMapping("/{id}/difunto")
    public ResponseEntity<List<DifuntoDTO>> getDifuntos(@PathVariable Long id){
        return ResponseEntity.ok(difuntoService.findByParcelaId(id));
    }

    @PostMapping("/{id}/difunto")
    public ResponseEntity<DifuntoDTO> saveDifunto(@PathVariable Long id, @Valid @RequestBody DifuntoDTO difuntoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(difuntoService.save(id, difuntoDTO));
    }

    @GetMapping("/{id}/servicio")
    public ResponseEntity<List<ServicioDTO>> getServicios(@PathVariable Long id){
        return ResponseEntity.ok(servicioService.findByParcelaId(id));
    }

    @PostMapping("/{id}/servicio")
    public ResponseEntity<ServicioDTO> saveServicio(@PathVariable Long id, @Valid @RequestBody ServicioDTO servicioDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioService.save(id, servicioDTO));
    }    

    @GetMapping("/{id}/concesion")
    public ResponseEntity<ConcesionDTO> getConcesion(@PathVariable Long id){
        return ResponseEntity.ok(concesionService.findByParcelaId(id));
    }

    @PostMapping("/{id}/concesion")
    public ResponseEntity<ConcesionDTO> saveConcesion(@PathVariable Long id, @Valid @RequestBody ConcesionDTO concesionDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(concesionService.save(id, concesionDTO));
    }

}