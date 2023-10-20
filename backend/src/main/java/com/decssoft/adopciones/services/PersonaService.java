package com.decssoft.adopciones.services;

import com.decssoft.adopciones.entities.Persona;
import com.decssoft.adopciones.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 *
 * @author mis_p
 */
@Service
public class PersonaService {

    @Autowired
    PersonaRepository repository;

    public Page<Persona> getPersonas(Integer page, Integer quantity) {
        return repository.findAll(PageRequest.of(page, quantity));
    }
}
