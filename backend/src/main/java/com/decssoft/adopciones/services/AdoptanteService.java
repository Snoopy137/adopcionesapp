package com.decssoft.adopciones.services;

import com.decssoft.adopciones.entities.Adoptante;
import com.decssoft.adopciones.repositories.AdoptanteRespository;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
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
public class AdoptanteService {

    @Autowired
    AdoptanteRespository repository;

    @Value("${assets}")
    String assets;

    public Page<Adoptante> getAdoptantes(Integer page, Integer quantity) {
        return repository.findAll(PageRequest.of(page, quantity));
    }

    public Page<Adoptante> getAdoptantes(String name, Integer page, Integer quantity) {
        return repository.findByNombreStartsWith(name, PageRequest.of(page, quantity));
    }

    public Page<Adoptante> getAdoptantes(Boolean listaNegra, Integer page, Integer quantity) {
        return repository.findByListaNegra(listaNegra, PageRequest.of(page, quantity));
    }

    public Page<Adoptante> getAdoptantesEmail(String email, Integer page, Integer quantity) {
        return repository.findByEmailStartsWith(email, PageRequest.of(page, quantity));
    }

    public Adoptante getAdoptante(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("No se encontro el adoptante"));
    }

    public Adoptante createAdoptante(Adoptante adoptante, MultipartFile foto) throws IOException {
        adoptante.setPicture(foto.getOriginalFilename());
        Adoptante savedAdoptante = repository.save(adoptante);
        if (foto != null) {
            File file = new File(assets + "\\" + savedAdoptante.getIdPersona() + foto.getOriginalFilename());
            if (file.exists()) {
                file.delete();
            }
            Files.copy(foto.getInputStream(), Paths.get(assets).resolve(file.getName()));
        }
        return savedAdoptante;
    }

    public void deleteAdoptante(Integer adoptante) {
        Adoptante adop = getAdoptante(adoptante);
        repository.delete(adop);
    }

    public Adoptante modifyAdoptante(Adoptante adoptante, MultipartFile foto) throws IOException {
        Adoptante adoptanteExist = repository.findById(adoptante.getIdPersona()).orElseThrow(() -> new NoSuchElementException("El usuario no existe, refresque la página para actualizar la información"));
        if (foto == null) adoptante.setPicture(adoptanteExist.getPicture());
        else {
            if (foto != null) {
                File file = new File(assets + "\\" + adoptante.getIdPersona() + foto.getOriginalFilename());
                if (file.exists()) {
                    file.delete();
                }
                Files.copy(foto.getInputStream(), Paths.get(assets).resolve(file.getName()));
                adoptante.setPicture(foto.getOriginalFilename());
            }
        }
        return repository.save(adoptante);
    }
}
