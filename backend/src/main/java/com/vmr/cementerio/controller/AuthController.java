package com.vmr.cementerio.controller;

import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import com.vmr.cementerio.security.JwtService;
import org.springframework.security.core.Authentication;
import com.vmr.cementerio.dto.request.LoginRequest;
import com.vmr.cementerio.enums.TipoRol;
import com.vmr.cementerio.security.TokenResponse;
import com.vmr.cementerio.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import com.vmr.cementerio.model.Usuario;
import com.vmr.cementerio.security.AuthenticationFacade;
import com.vmr.cementerio.dto.response.UsuarioDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthenticationFacade authenticationFacade;
    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), 
                loginRequest.getContrasena()
            )
        );

        Usuario usuario = (Usuario) authentication.getPrincipal();
        String token = jwtService.generateToken(usuario);
        return ResponseEntity.ok(new TokenResponse(token));
    }

    @GetMapping("/who")
    public ResponseEntity<UsuarioDTO> who(){
        return ResponseEntity.ok(usuarioService.findByEmail(authenticationFacade.getAuthenticatedUsuario().getEmail()));
    }

    // @GetMapping("/rol")
    // public ResponseEntity<TipoRol> saluda(){
    //     Usuario usuario = authenticationFacade.getAuthenticatedUsuario();
    //     return ResponseEntity.ok(usuario.getRol().getNombre());
    // }

}
