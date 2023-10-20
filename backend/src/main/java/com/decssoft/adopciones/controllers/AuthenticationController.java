package com.decssoft.adopciones.controllers;

import com.decssoft.adopciones.repositories.UsuarioRepository;
import com.decssoft.adopciones.security.AuthenticationRequest;
import com.decssoft.adopciones.security.AuthenticationResponse;
import com.decssoft.adopciones.security.JwtTokenUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mis_p
 */
@RestController
@RequestMapping("/api/v1/authentication")
@Slf4j
public class AuthenticationController {

    @Autowired
    JwtTokenUtil jwtUtil;
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    UsuarioRepository repository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationRequest request) throws Exception {
        var user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new BadCredentialsException("Credenciales incorrectas"));
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var jwtToken = jwtUtil.generateAccessToken(user);
        AuthenticationResponse response = new AuthenticationResponse(user.getEmail(), jwtToken, user.getType());
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
