package com.decssoft.adopciones.controllers;

import com.decssoft.adopciones.entities.Transito;
import com.decssoft.adopciones.services.TransitoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping("/api/v1/transito")
public class TransitoController {

    @Autowired
    TransitoService service;

    @GetMapping("/listar")
    public ResponseEntity<Page<Transito>> listTransitos(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer quantity) {
        return new ResponseEntity(service.getTransitos(page, quantity), HttpStatus.OK);
    }

    @GetMapping("/transito")
    public ResponseEntity<Page<Transito>> getTransitos(@RequestParam String name,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer quantity) {
        return new ResponseEntity(service.getTransitos(name, page, quantity), HttpStatus.OK);
    }

    @GetMapping("/disponible")
    public ResponseEntity<Page<Transito>> getTransitos(@RequestParam Boolean disponible,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer quantity) {
        return new ResponseEntity(service.getTransitos(disponible, page, quantity), HttpStatus.OK);
    }

    @GetMapping("/email")
    public ResponseEntity<Page<Transito>> getTransitosEmail(@RequestParam String email,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer quantity) {
        return new ResponseEntity(service.getTransitosEmail(email, page, quantity), HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<Transito> getTransitoId(@RequestParam Integer id) {
        return new ResponseEntity(service.getTransitoid(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Transito> createTransito(@RequestBody @Valid Transito transito) {
        return new ResponseEntity(service.createTransito(transito), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTransito(@RequestBody Transito transito) {
        return new ResponseEntity("Transito eliminado", HttpStatus.OK);
    }
}
