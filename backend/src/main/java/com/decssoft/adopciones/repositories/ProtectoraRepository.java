package com.decssoft.adopciones.repositories;

import com.decssoft.adopciones.entities.Protectora;
import com.decssoft.adopciones.entities.Usuario;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mis_p
 */
@Repository
public interface ProtectoraRepository extends JpaRepository<Protectora, Integer> {

    public Page<Protectora> findByNombreStartsWith(String protectora, Pageable pageable);

    public Boolean existsByEmail(String email);

    public Page<Protectora> findAll(Pageable pageable);

    public Optional<Protectora> findByEncargado(Usuario usuario);
}
