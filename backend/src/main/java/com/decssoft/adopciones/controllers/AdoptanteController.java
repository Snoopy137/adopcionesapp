package com.decssoft.adopciones.controllers;

import com.decssoft.adopciones.entities.Adoptante;
import com.decssoft.adopciones.services.AdoptanteService;
import jakarta.validation.Valid;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping("/api/v1/adoptante")
@Slf4j
public class AdoptanteController {

    @Autowired
    AdoptanteService service;

    @GetMapping("/list")
    public ResponseEntity<Page<Adoptante>> listAdoptantes(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer quantity) {
        return new ResponseEntity(service.getAdoptantes(page, quantity), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Adoptante> createAdoptante(@RequestPart @Valid Adoptante adoptante, @RequestPart(required = false) MultipartFile foto) throws IOException {
        return new ResponseEntity(service.createAdoptante(adoptante, foto), HttpStatus.CREATED);
    }

    @PatchMapping("/modify")
    public ResponseEntity<Adoptante> modifyAdoptante(@RequestPart @Valid Adoptante adoptante, @RequestPart(required = false) MultipartFile foto) throws IOException {
        log.info("modifying");
        return new ResponseEntity(service.modifyAdoptante(adoptante, foto), HttpStatus.CREATED);
    }
}
