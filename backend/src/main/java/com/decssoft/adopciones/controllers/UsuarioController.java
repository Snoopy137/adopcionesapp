package com.decssoft.adopciones.controllers;

import com.decssoft.adopciones.DTOs.UsuarioModificationDTO;
import com.decssoft.adopciones.commons.Role;
import com.decssoft.adopciones.entities.Usuario;
import com.decssoft.adopciones.services.UsuarioService;
import jakarta.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mis_p
 */
@RestController
@RequestMapping("/api/v1/usuario")
@Slf4j
public class UsuarioController {

    @Autowired
    UsuarioService service;

    @GetMapping("/list")
    public ResponseEntity<?> getUsuarios(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer quantity) {
        return new ResponseEntity(service.getUsuarios(page, quantity), HttpStatus.OK);
    }

    @GetMapping("/usuario")
    public ResponseEntity<?> getUsuario(@RequestParam Integer id) {
        return new ResponseEntity(service.getUsuario(id), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity getUsuario(@RequestParam String name,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer quantity) {
        return new ResponseEntity(service.getUsuario(name, page, quantity), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUsuario(@RequestBody Usuario usuario) {
        service.deleteUsuario(usuario);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("mensaje", "Se elimino el Usuario " + usuario.getNombre());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUsuario(@RequestBody @Valid Usuario usuario) {
        return new ResponseEntity(service.createUsuario(usuario), HttpStatus.OK);
    }

    @GetMapping("/byemail")
    public ResponseEntity<?> getByEmail(@RequestParam String email) {
        return new ResponseEntity(service.getByMail(email), HttpStatus.OK);
    }

    @GetMapping("/byrole")
    public ResponseEntity<?> getByRole(@RequestParam String role,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer quantity) {
        return new ResponseEntity(service.getByRole(Role.valueOf(role), page, quantity), HttpStatus.OK);
    }

    @GetMapping("/byprotectora")
    public ResponseEntity<?> getByProtectora(@RequestParam Integer protectora,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer quantity) {
        return new ResponseEntity(service.getByProtectora(protectora, page, quantity), HttpStatus.OK);
    }

    @PostMapping("/modify")
    public ResponseEntity<?> modifyUsuario(@RequestBody @Valid UsuarioModificationDTO usuario) {
        return new ResponseEntity(service.createUsuario(usuario.toUsuario()), HttpStatus.OK);
    }
}
