package com.decssoft.adopciones.repositories;

import com.decssoft.adopciones.commons.Role;
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
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    public Page<Usuario> findByNombreStartsWith(String name, Pageable page);

    public Boolean existsByEmail(String email);

    public Optional<Usuario> findByEmail(String email);

    public Page<Usuario> findByType(Role role, Pageable page);

    public Page<Usuario> findByProtectoras(Protectora protectora, Pageable page);

}
