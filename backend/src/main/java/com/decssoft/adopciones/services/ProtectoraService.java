package com.decssoft.adopciones.services;

import com.decssoft.adopciones.entities.Protectora;
import com.decssoft.adopciones.entities.Usuario;
import com.decssoft.adopciones.repositories.ProtectoraRepository;
import com.decssoft.adopciones.security.JwtTokenUtil;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

/**
 *
 * @author mis_p
 */
@Service
@Slf4j
public class ProtectoraService {

    @Autowired
    ProtectoraRepository repository;

    @Autowired
    JwtTokenUtil util;

    public Page<Protectora> getProtectoras(Integer page, Integer quantity) {
        return repository.findAll(PageRequest.of(page, quantity));
    }

    public Protectora getProtectora(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    public Page<Protectora> getProtectora(String name, Integer page, Integer quantity) {
        return repository.findByNombreStartsWith(name, PageRequest.of(page, quantity));
    }

    public Protectora createProtectora(Protectora protectora) {
        return repository.save(protectora);
    }

    public void deleteProtectora(Protectora protectora) {
        repository.delete(protectora);
    }

    public Boolean existeProtectora(String email) {
        return repository.existsByEmail(email);
    }

    public Protectora getByEncargdo(Usuario usuario) {
        return repository.findByEncargado(usuario).orElseThrow(() -> new NoSuchElementException("No se encontro ninguna protectora con ese encargado"));
    }

    public Protectora modifyProtectora(Protectora protectora, String token) {
        var id = Integer.valueOf(util.extractClaim(token.substring(7), t -> t.get("id").toString()));
        if (protectora.getEncargado().getIdPersona() != id) {
            throw new AccessDeniedException("Solo el encargado puede modificar la protectora");
        }
        return repository.save(protectora);
    }
}
