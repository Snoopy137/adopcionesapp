package com.decssoft.adopciones.controllers;

import com.decssoft.adopciones.commons.Genero;
import com.decssoft.adopciones.entities.Adoptante;
import com.decssoft.adopciones.entities.Gato;
import com.decssoft.adopciones.services.GatoService;
import jakarta.validation.Valid;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author mis_p
 */
@RestController
@RequestMapping("/api/v1/gato/")
public class GatoController {

    @Autowired
    GatoService service;

    @GetMapping("/byapto")
    public ResponseEntity<?> getGatos(@RequestParam Boolean apto,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer quantity) {
        return new ResponseEntity(service.getGatos(apto, page, quantity), HttpStatus.OK);
    }

    @GetMapping("/bynombre")
    public ResponseEntity<?> getGatos(@RequestParam String nombre,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer quantity) {
        return new ResponseEntity(service.getGatos(nombre, page, quantity), HttpStatus.OK);
    }

    @GetMapping("/bygenero")
    public ResponseEntity<?> getGatoGenero(@RequestParam String genero,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer quantity) {
        return new ResponseEntity(service.getGatos(Genero.valueOf(genero), page, quantity), HttpStatus.OK);
    }

    @GetMapping("/byadoptante")
    public ResponseEntity<?> getGatoAdoptante(@RequestParam Integer adoptanteId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer quantity) {
        Adoptante adoptante = Adoptante.builder().idPersona(adoptanteId).build();
        return new ResponseEntity(service.getGatos(adoptante, page, quantity), HttpStatus.OK);
    }

    @GetMapping("/animal")
    public ResponseEntity<?> getGatoId(@RequestParam Integer id) {
        return new ResponseEntity(service.getGato(id), HttpStatus.OK);
    }

    @GetMapping("/animales")
    public ResponseEntity<?> getGatos(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer quantity) {
        return new ResponseEntity(service.getGatos(page, quantity), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createGato(@RequestPart @Valid Gato gato, @RequestPart(required = false) MultipartFile foto) throws IOException {
        return new ResponseEntity(service.createGato(gato, foto), HttpStatus.CREATED);
    }

    @PatchMapping("/modify")
    public ResponseEntity<?> modifyGato(@RequestPart @Valid Gato gato, @RequestPart(required = false) MultipartFile foto) throws IOException {
        return new ResponseEntity(service.modifyGato(gato, foto), HttpStatus.CREATED);
    }
}
