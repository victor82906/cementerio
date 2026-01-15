package com.vmr.cementerio.controller;

import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.vmr.cementerio.service.CiudadanoService;
import jakarta.validation.Valid;
import com.vmr.cementerio.model.Ciudadano;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import com.vmr.cementerio.security.JwtService;
import org.springframework.security.core.Authentication;
import com.vmr.cementerio.dto.LoginRequest;
import com.vmr.cementerio.security.TokenResponse;
import org.springframework.web.bind.annotation.GetMapping;
import com.vmr.cementerio.model.Usuario;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final CiudadanoService ciudadanoService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/registerCiudadano")
    public ResponseEntity<Ciudadano> registerCiudadano(@RequestBody @Valid Ciudadano ciudadano) {
        Ciudadano nuevoCiudadano = ciudadanoService.registrarUsuario(ciudadano);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCiudadano);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getContrasena())
        );

        Usuario usuario = (Usuario) authentication.getPrincipal();
        String token = jwtService.generateToken(usuario);

        return ResponseEntity.ok(new TokenResponse(token));
    }

    @GetMapping("/saluda")
    public ResponseEntity<String> saluda(){
        return ResponseEntity.ok("Hola");
    }

}
