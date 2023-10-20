package com.decssoft.adopciones.repositories;

import com.decssoft.adopciones.entities.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mis_p
 */
@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    public Page<Persona> findAll(Pageable page);
}
