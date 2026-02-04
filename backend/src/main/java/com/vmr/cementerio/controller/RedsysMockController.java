package com.vmr.cementerio.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.vmr.cementerio.model.Pago;
import com.vmr.cementerio.repository.PagoRepository;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mock-redsys")
public class RedsysMockController {
    
    private final PagoRepository pagoRepository;

    @GetMapping("/pay")
    public String pay(@RequestParam("orderId") Long orderId, Model model) {
        Pago pago = pagoRepository.findById(orderId).orElseThrow();

        model.addAttribute("id", pago.getId());
        model.addAttribute("monto", pago.getMonto());

        return "redsys-mock";
    }

    @PostMapping("/result")
    public String result(@RequestParam String id, @RequestParam String responseCode){
        RestTemplate rest = new RestTemplate();
        rest.postForEntity(
            "http://localhost:8081/pago/callback",
            Map.of("id", Long.parseLong(id), "responseCode", responseCode), Void.class
        );
        return "redirect:http://localhost:4200/resultado?id=" + id;
    }

}
