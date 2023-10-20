package com.decssoft.adopciones.repositories;

import com.decssoft.adopciones.commons.Estado;
import com.decssoft.adopciones.commons.Genero;
import com.decssoft.adopciones.commons.Tamanio;
import com.decssoft.adopciones.entities.Adoptante;
import com.decssoft.adopciones.entities.Perro;
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
public interface PerroRepository extends JpaRepository<Perro, Integer> {

    public Page<Perro> findByTamanio(Tamanio tamanio, Pageable page);

    public Page<Perro> findByEstado(Estado estado, Pageable page);

    public Page<Perro> findByNombreStartsWith(String name, Pageable page);

    public Page<Perro> findByGenero(Genero genero, Pageable page);

    public Page<Perro> findByAdoptante(Adoptante adoptante, Pageable page);

    public Page<Perro> findByProtectora(Protectora protectra, Pageable page);

    public Page<Perro> findAll(Pageable page);
}
