package com.decssoft.adopciones.services;

import com.decssoft.adopciones.commons.Estado;
import com.decssoft.adopciones.commons.Genero;
import com.decssoft.adopciones.entities.Adoptante;
import com.decssoft.adopciones.entities.Animal;
import com.decssoft.adopciones.repositories.AnimalRepository;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 *
 * @author mis_p
 */
@Service
public class AnimalService {

    @Autowired
    AnimalRepository repository;

    public Page<Animal> getAnimales(Estado estado, Integer page, Integer quantity) {
        return repository.findByEstado(estado, PageRequest.of(page, quantity));
    }

    public Page<Animal> getAnimales(String nombre, Integer page, Integer quantity) {
        return repository.findByNombreStartsWith(nombre, PageRequest.of(page, quantity));
    }

    public Page<Animal> getAnimales(Genero genero, Integer page, Integer quantity) {
        return repository.findByGenero(genero, PageRequest.of(page, quantity));
    }

    public Page<Animal> getAnimales(Adoptante adoptante, Integer page, Integer quantity) {
        return repository.findByAdoptante(adoptante, PageRequest.of(page, quantity));
    }

    public Page<Animal> getAnimales(Integer protectora, Integer page, Integer quantity) {
        try {
            return repository.findByProtectora_IdProtectora(protectora, PageRequest.of(page, quantity));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Animal getAnimal(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("El usuario no existe, refresque la página para actualizar la información"));
    }

    public Page<Animal> getAnimales(Integer page, Integer quantity) {
        return repository.findAll(PageRequest.of(page, quantity));
    }

    public void deleteAnimal(Animal animal) {
        repository.delete(animal);
    }
}
