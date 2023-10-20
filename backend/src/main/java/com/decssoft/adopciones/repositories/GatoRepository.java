package com.decssoft.adopciones.repositories;

import com.decssoft.adopciones.commons.Estado;
import com.decssoft.adopciones.commons.Genero;
import com.decssoft.adopciones.entities.Adoptante;
import com.decssoft.adopciones.entities.Gato;
import com.decssoft.adopciones.entities.Protectora;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mis_p
 */
@Repository
public interface GatoRepository extends JpaRepository<Gato, Integer> {

    public Page<Gato> findByAptoDepto(Boolean apto, Pageable page);

    public Page<Gato> findByEstado(Estado estado, Pageable page);

    public Page<Gato> findByNombreStartsWith(String name, Pageable page);

    public Page<Gato> findByGenero(Genero genero, Pageable page);

    public Page<Gato> findByAdoptante(Adoptante adoptante, Pageable page);

    public Page<Gato> findByProtectora(Protectora protectra, Pageable page);
}
