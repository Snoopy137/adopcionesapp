package com.decssoft.adopciones.repositories;

import com.decssoft.adopciones.entities.Transito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mis_p
 */
@Repository
public interface TransitoRepository extends JpaRepository<Transito, Integer> {

    public Page<Transito> findAll(Pageable page);

    public Page<Transito> findByNombreStartsWith(String nombre, Pageable page);

    public Page<Transito> findByDisponible(Boolean disponible, Pageable page);

    public Page<Transito> findByEmailStartsWith(String email, Pageable page);

}
