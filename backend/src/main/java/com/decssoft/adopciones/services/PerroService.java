package com.decssoft.adopciones.services;

import com.decssoft.adopciones.commons.Genero;
import com.decssoft.adopciones.commons.Tamanio;
import com.decssoft.adopciones.entities.Adoptante;
import com.decssoft.adopciones.entities.Perro;
import com.decssoft.adopciones.entities.Protectora;
import com.decssoft.adopciones.repositories.PerroRepository;
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
public class PerroService {

    @Autowired
    PerroRepository repository;

    @Value("${assets}")
    String assets;

    public Page<Perro> getPerros(Tamanio tamanio, Integer page, Integer quantity) {
        return repository.findByTamanio(tamanio, PageRequest.of(page, quantity));
    }

    public Page<Perro> getPerros(String nombre, Integer page, Integer quantity) {
        return repository.findByNombreStartsWith(nombre, PageRequest.of(page, quantity));
    }

    public Page<Perro> getPerros(Genero genero, Integer page, Integer quantity) {
        return repository.findByGenero(genero, PageRequest.of(page, quantity));
    }

    public Page<Perro> getPerros(Adoptante adoptante, Integer page, Integer quantity) {
        return repository.findByAdoptante(adoptante, PageRequest.of(page, quantity));
    }

    public Page<Perro> getPerros(Protectora protectora, Integer page, Integer quantity) {
        return repository.findByProtectora(protectora, PageRequest.of(page, quantity));
    }

    public Perro getPerro(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("El perro no existe, refresque la página para actualizar la información"));
    }

    public Page<Perro> getPerros(Integer page, Integer quantity) {
        return repository.findAll(PageRequest.of(page, quantity));
    }

    public Perro createPerro(Perro perro, MultipartFile foto) throws IOException {
        perro.setFoto(foto.getOriginalFilename());
        Perro savedPerro = repository.save(perro);
        if (foto != null) {
            File file = new File(assets + "\\" + savedPerro.getAnimalid() + foto.getOriginalFilename());
            if (file.exists()) {
                file.delete();
            }
            Files.copy(foto.getInputStream(), Paths.get(assets).resolve(file.getName()));
        }
        return savedPerro;
    }

    public Perro modifyPerro(Perro perro, MultipartFile foto) throws IOException {
        Perro perroExist = repository.findById(perro.getAnimalid()).orElseThrow(() -> new NoSuchElementException("El usuario no existe, refresque la página para actualizar la información"));
        if (foto == null) perro.setFoto(perroExist.getFoto());
        else {
            if (foto != null) {
                File file = new File(assets + "\\" + perro.getAnimalid() + foto.getOriginalFilename());
                if (file.exists()) {
                    file.delete();
                }
                Files.copy(foto.getInputStream(), Paths.get(assets).resolve(file.getName()));
                perro.setFoto(foto.getOriginalFilename());
            }
        }
        return repository.save(perro);
    }
}
