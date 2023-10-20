package com.decssoft.adopciones.controllers;

import com.decssoft.adopciones.commons.Role;
import static com.decssoft.adopciones.commons.Role.ENCARGADO;
import com.decssoft.adopciones.entities.Protectora;
import com.decssoft.adopciones.entities.Usuario;
import com.decssoft.adopciones.security.JwtTokenUtil;
import com.decssoft.adopciones.services.ProtectoraService;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mis_p
 */
@RestController
@RequestMapping("/api/v1/protectora")
@Slf4j
public class ProtectoraController {

    @Autowired
    ProtectoraService service;

    @Autowired
    UsuarioService serviceUsuario;

    @Autowired
    JwtTokenUtil util;

    @PostMapping("/create")
    public ResponseEntity<?> createProtectora(@RequestBody @Valid Protectora protectora) {
        Map<String, Object> respuesta = new LinkedHashMap<>();
        if (serviceUsuario.existeUsuario(protectora.getEncargado().getEmail())) {
            respuesta.put("mensaje", "Ya existe un encargado registrado con ese email");
            return new ResponseEntity(respuesta, HttpStatus.CONFLICT);
        }
        if (service.existeProtectora(protectora.getEmail())) {
            respuesta.put("mensaje", "Ya existe una protectroa registrada con ese email");
            return new ResponseEntity(respuesta, HttpStatus.CONFLICT);
        }
        protectora.getEncargado().setType(ENCARGADO);
        Usuario usuario = new Usuario(serviceUsuario.createUsuario(protectora.getEncargado()));
        return new ResponseEntity(service.createProtectora(protectora), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listProtectora(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "6") Integer quantity) {
        return new ResponseEntity(service.getProtectoras(page, quantity), HttpStatus.OK);
    }

    @GetMapping("/protectora")
    public ResponseEntity<?> getProtectora(@RequestParam Integer id) {
        return new ResponseEntity(service.getProtectora(id), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getProtectora(@RequestParam String name,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer quantity) {
        return new ResponseEntity(service.getProtectora(name, page, quantity), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProtectora(@RequestBody Protectora protectora) {
        service.deleteProtectora(protectora);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("Mensaje", "se elimino la protectora " + protectora.getNombre());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/encargado")
    public ResponseEntity<Protectora> getProtectora(@RequestParam String encargado) {
        Usuario usuario = new Usuario(serviceUsuario.getByMail(encargado));
        if (usuario.getType() == Role.ENCARGADO) {
            return new ResponseEntity(service.getByEncargdo(usuario), HttpStatus.OK);
        } else {
            return new ResponseEntity(usuario.getProtectoras(), HttpStatus.OK);
        }
    }

    @PatchMapping("/modificar")
    public ResponseEntity<Protectora> modifyProtectora(@RequestBody @Valid Protectora protectora,
            @RequestHeader("Authorization") String token) {
        return new ResponseEntity(service.modifyProtectora(protectora, token), HttpStatus.OK);
    }
}
