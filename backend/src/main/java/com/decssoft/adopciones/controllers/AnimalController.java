package com.decssoft.adopciones.controllers;

import com.decssoft.adopciones.commons.Estado;
import com.decssoft.adopciones.commons.Genero;
import com.decssoft.adopciones.entities.Adoptante;
import com.decssoft.adopciones.entities.Animal;
import com.decssoft.adopciones.services.AnimalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mis_p
 */
@RestController
@RequestMapping("/api/v1/animal")
@Slf4j
public class AnimalController {

    @Autowired
    AnimalService service;

    @GetMapping("/byestado")
    public ResponseEntity<?> getAnimalesEstado(@RequestParam String estado,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer quantity) {
        return new ResponseEntity(service.getAnimales(Estado.valueOf(estado), page, quantity), HttpStatus.OK);
    }

    @GetMapping("/bynombre")
    public ResponseEntity<?> getAnimales(@RequestParam String nombre,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer quantity) {
        return new ResponseEntity(service.getAnimales(nombre, page, quantity), HttpStatus.OK);
    }

    @GetMapping("/bygenero")
    public ResponseEntity<?> getAnimalesGenero(@RequestParam String genero,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer quantity) {
        return new ResponseEntity(service.getAnimales(Genero.valueOf(genero), page, quantity), HttpStatus.OK);
    }

    @GetMapping("/byadoptante")
    public ResponseEntity<?> getAnimales(@RequestParam Integer adoptanteId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer quantity) {
        Adoptante adoptante = Adoptante.builder().idPersona(adoptanteId).build();
        return new ResponseEntity(service.getAnimales(adoptante, page, quantity), HttpStatus.OK);
    }

    @GetMapping("/animal")
    public ResponseEntity<?> getAnimal(@RequestParam Integer id) {
        return new ResponseEntity(service.getAnimal(id), HttpStatus.OK);
    }

    @GetMapping("/animales")
    public ResponseEntity<?> getAnimales(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer quantity) {
        return new ResponseEntity(service.getAnimales(page, quantity), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAnimal(@RequestBody Animal animal) {
        return new ResponseEntity("El animal fue borrado", HttpStatus.OK);
    }

    @GetMapping("/byprotectora")
    public ResponseEntity<?> getAnimalesByProtectora(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer quantity,
            @RequestParam Integer protectora) {
        return new ResponseEntity(service.getAnimales(protectora, page, quantity), HttpStatus.OK);
    }
}
