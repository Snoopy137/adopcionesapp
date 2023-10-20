package com.decssoft.adopciones.services;

import com.decssoft.adopciones.commons.Genero;
import com.decssoft.adopciones.entities.Adoptante;
import com.decssoft.adopciones.entities.Gato;
import com.decssoft.adopciones.entities.Protectora;
import com.decssoft.adopciones.repositories.GatoRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author mis_p
 */
@Service
@Slf4j
public class GatoService {

    @Autowired
    GatoRepository repository;

    @Value("${assets}")
    String assets;

    public Page<Gato> getGatos(Boolean apto, Integer page, Integer quantity) {
        return repository.findByAptoDepto(apto, PageRequest.of(page, quantity));
    }

    public Page<Gato> getGatos(String nombre, Integer page, Integer quantity) {
        return repository.findByNombreStartsWith(nombre, PageRequest.of(page, quantity));
    }

    public Page<Gato> getGatos(Genero genero, Integer page, Integer quantity) {
        return repository.findByGenero(genero, PageRequest.of(page, quantity));
    }

    public Page<Gato> getGatos(Adoptante adoptante, Integer page, Integer quantity) {
        return repository.findByAdoptante(adoptante, PageRequest.of(page, quantity));
    }

    public Page<Gato> getGatos(Protectora protectora, Integer page, Integer quantity) {
        return repository.findByProtectora(protectora, PageRequest.of(page, quantity));
    }

    public Gato getGato(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("El usuario no existe, refresque la página para actualizar la información"));
    }

    public Page<Gato> getGatos(Integer page, Integer quantity) {
        return repository.findAll(PageRequest.of(page, quantity));
    }

    public Gato createGato(Gato gato, MultipartFile foto) throws IOException {
        if (foto != null) gato.setFoto(foto.getOriginalFilename());
        Gato savedGato = repository.save(gato);
        if (foto != null) {
            File file = new File(assets + "\\" + savedGato.getAnimalid() + foto.getOriginalFilename());
            if (file.exists()) {
                file.delete();
            }
            Files.copy(foto.getInputStream(), Paths.get(assets).resolve(file.getName()));
        }
        return savedGato;
    }

    public Gato modifyGato(Gato gato, MultipartFile foto) throws IOException {
        Gato gatoExist = repository.findById(gato.getAnimalid()).orElseThrow(() -> new NoSuchElementException("El usuario no existe, refresque la página para actualizar la información"));
        if (foto == null) gato.setFoto(gatoExist.getFoto());
        else {
            if (foto != null) {
                File file = new File(assets + "\\" + gato.getAnimalid() + foto.getOriginalFilename());
                if (file.exists()) {
                    file.delete();
                }
                Files.copy(foto.getInputStream(), Paths.get(assets).resolve(file.getName()));
                gato.setFoto(foto.getOriginalFilename());
            }
        }
        return repository.save(gato);
    }
}
