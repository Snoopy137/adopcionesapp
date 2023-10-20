package com.decssoft.adopciones.services;

import com.decssoft.adopciones.commons.Role;
import com.decssoft.adopciones.entities.Transito;
import com.decssoft.adopciones.repositories.AnimalRepository;
import com.decssoft.adopciones.repositories.TransitoRepository;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author mis_p
 */
@Service
public class TransitoService {

    @Autowired
    TransitoRepository repository;

    @Autowired
    AnimalRepository repoAnimal;

    @Autowired
    PasswordEncoder encoder;

    public Page getTransitos(Integer page, Integer quantity) {
        return repository.findAll(PageRequest.of(page, quantity));
    }

    public Page getTransitos(String nombre, Integer page, Integer quantity) {
        return repository.findByNombreStartsWith(nombre, PageRequest.of(page, quantity));
    }

    public Page getTransitos(Boolean disponible, Integer page, Integer quantity) {
        return repository.findByDisponible(disponible, PageRequest.of(page, quantity));
    }

    public Page getTransitosEmail(String email, Integer page, Integer quantity) {
        return repository.findByEmailStartsWith(email, PageRequest.of(page, quantity));
    }

    public Transito getTransitoid(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("No se encontro ningun transito, revise los datos ingresados"));
    }

    public Transito createTransito(Transito transito) {
        transito.setContrasenia(encoder.encode(transito.getContrasenia()));
        transito.setType(Role.TRANSITO);
        return repository.save(transito);
    }

    public void deleteTransito(Transito transito) {
        repository.delete(transito);
    }
}
