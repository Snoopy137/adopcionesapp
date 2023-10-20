package com.decssoft.adopciones.controllers;

import com.decssoft.adopciones.entities.Persona;
import com.decssoft.adopciones.services.PersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mis_p
 */
@RestController
@RequestMapping("/api/v1/persona")
public class PersonaController {

    @Autowired
    PersonaService service;

    @GetMapping("/list")
    public ResponseEntity<List<Persona>> listPersonas(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer quantity) {
        return new ResponseEntity(service.getPersonas(page, quantity), HttpStatus.OK);
    }

}
