package com.decssoft.adopciones.repositories;

import com.decssoft.adopciones.entities.Adoptante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mis_p
 */
@Repository
public interface AdoptanteRespository extends JpaRepository<Adoptante, Integer> {

    public Page<Adoptante> findAll(Pageable page);

    public Page<Adoptante> findByNombreStartsWith(String nombre, Pageable page);

    public Page<Adoptante> findByListaNegra(Boolean ListaNegra, Pageable page);

    public Page<Adoptante> findByEmailStartsWith(String email, Pageable page);

}
