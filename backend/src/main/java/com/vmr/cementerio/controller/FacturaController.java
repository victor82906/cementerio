package com.vmr.cementerio.controller;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vmr.cementerio.model.Factura;
import com.vmr.cementerio.service.FacturaService;
import com.vmr.cementerio.service.impl.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/factura")
public class FacturaController {
    
    private final FacturaService facturaService;
    private final PdfService pdfService;

    @GetMapping("/{id}/descargar")
    @PreAuthorize("hasRole('ADMIN') or " +
        "(hasRole('CIUDADANO') and @facturaRepository.existsByIdAndConcesion_Ciudadano_IdOrIdAndServicio_Parcela_Concesion_Ciudadano_Id(#id, principal.id, #id, principal.id)) or " +
        "(hasRole('AYUNTAMIENTO') and @facturaRepository.existsByIdAndConcesion_Parcela_Zona_Cementerio_Ayuntamiento_IdOrIdAndServicio_Parcela_Zona_Cementerio_Ayuntamiento_Id(#id, principal.id, #id, principal.id))")
    public ResponseEntity<byte[]> descargarFactura(@PathVariable Long id) {
        
        // 1. Recuperar la factura de la BD
        Factura factura = facturaService.findById(id);

        // 2. Preparar datos para la plantilla
        Map<String, Object> datos = new HashMap<>();
        datos.put("factura", factura);

        // 3. Generar el PDF usando la plantilla "factura.html"
        byte[] pdfBytes = pdfService.generarPdf("factura", datos);

        // 4. Devolver el archivo para descarga
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Factura_" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }



}
