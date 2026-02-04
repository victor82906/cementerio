package com.vmr.cementerio.controller;

import org.springframework.web.bind.annotation.RestController;
import com.vmr.cementerio.enums.EstadoPago;
import com.vmr.cementerio.model.Pago;
import com.vmr.cementerio.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/pago")
public class PagoController {
    
    private final PagoRepository pagoRepository;

    @PostMapping("/init")
    public Map<String, String> init(@RequestBody Map<String, BigDecimal> body) {
        Pago pago = new Pago();
        pago.setMonto(body.get("monto"));
        pago.setEstado(EstadoPago.PENDIENTE);
        pago.setFechaPago(LocalDateTime.now());
        pagoRepository.save(pago);

        return Map.of("redirectUrl", "http://localhost:8081/mock-redsys/pay?orderId=" + pago.getId());
    }

    @PostMapping("/callback")
    public ResponseEntity<Void> callback(@RequestBody Map<String, String> body) {
        Pago pago = pagoRepository.findById(Long.parseLong(body.get("id"))).orElseThrow();

        if(pago.getEstado() != EstadoPago.PENDIENTE) {
            return ResponseEntity.ok().build();
        }

        if("0000".equals(body.get("responseCode"))) {
            pago.setEstado(EstadoPago.COMPLETADO);
        } else {
            pago.setEstado(EstadoPago.FALLIDO);
        }

        pagoRepository.save(pago);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public Pago get(@PathVariable Long id) {
        return pagoRepository.findById(id).orElseThrow();
    }

}
