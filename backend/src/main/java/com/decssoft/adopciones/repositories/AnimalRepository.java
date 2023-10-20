package com.decssoft.adopciones.repositories;

import com.decssoft.adopciones.commons.Estado;
import com.decssoft.adopciones.commons.Genero;
import com.decssoft.adopciones.entities.Adoptante;
import com.decssoft.adopciones.entities.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mis_p
 */
@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    public Page<Animal> findByEstado(Estado estado, Pageable page);

    public Page<Animal> findByNombreStartsWith(String name, Pageable page);

    public Page<Animal> findByGenero(Genero genero, Pageable page);

    public Page<Animal> findByAdoptante(Adoptante adoptante, Pageable page);

    public Page<Animal> findByProtectora_IdProtectora(Integer protectra, Pageable page);
}
