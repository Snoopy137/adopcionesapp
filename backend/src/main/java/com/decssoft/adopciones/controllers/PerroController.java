package com.decssoft.adopciones.controllers;

import com.decssoft.adopciones.commons.Genero;
import com.decssoft.adopciones.commons.Tamanio;
import com.decssoft.adopciones.entities.Adoptante;
import com.decssoft.adopciones.entities.Perro;
import com.decssoft.adopciones.services.PerroService;
import jakarta.validation.Valid;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/api/v1/perro")
public class PerroController {

    @Autowired
    PerroService service;

    @GetMapping("/bytamanio")
    public ResponseEntity<?> getPerrosTamanio(@RequestParam String tamanio,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer quantity) {
        return new ResponseEntity(service.getPerros(Tamanio.valueOf(tamanio), page, quantity), HttpStatus.OK);
    }

    @GetMapping("/bynombre")
    public ResponseEntity<?> getPerros(@RequestParam String nombre,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer quantity) {
        return new ResponseEntity(service.getPerros(nombre, page, quantity), HttpStatus.OK);
    }

    @GetMapping("/bygenero")
    public ResponseEntity<?> getPerrosGenero(@RequestParam String genero,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer quantity) {
        return new ResponseEntity(service.getPerros(Genero.valueOf(genero), page, quantity), HttpStatus.OK);
    }

    @GetMapping("/byadoptante")
    public ResponseEntity<?> getPerros(@RequestParam Integer adoptanteId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer quantity) {
        Adoptante adoptante = Adoptante.builder().idPersona(adoptanteId).build();
        return new ResponseEntity(service.getPerros(adoptante, page, quantity), HttpStatus.OK);
    }

    @GetMapping("/animal")
    public ResponseEntity<?> getPerro(@RequestParam Integer id) {
        return new ResponseEntity(service.getPerro(id), HttpStatus.OK);
    }

    @GetMapping("/animales")
    public ResponseEntity<?> getPerros(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer quantity) {
        return new ResponseEntity(service.getPerros(page, quantity), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPerro(@RequestPart @Valid Perro perro, @RequestPart MultipartFile foto) throws IOException {
        return new ResponseEntity(service.createPerro(perro, foto), HttpStatus.CREATED);
    }
}
