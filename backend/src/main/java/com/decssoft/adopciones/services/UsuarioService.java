package com.decssoft.adopciones.services;

import com.decssoft.adopciones.DTOs.UsuarioResponseDTO;
import com.decssoft.adopciones.commons.Role;
import com.decssoft.adopciones.entities.Protectora;
import com.decssoft.adopciones.entities.Usuario;
import com.decssoft.adopciones.repositories.UsuarioRepository;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author mis_p
 */
@Service
@Slf4j
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    PasswordEncoder encoder;

    public Page<UsuarioResponseDTO> getUsuarios(Integer page, Integer quantity) {
        return repository.findAll(PageRequest.of(page, quantity))
                .map(u -> u.toResponseDTO());
    }

    public UsuarioResponseDTO getUsuario(Integer id) {
        Usuario u = repository.findById(id).orElseThrow(() -> new NoSuchElementException("El usuario no existe, refresque la página para actualizar la información"));
        return u.toResponseDTO();
    }

    public Page<UsuarioResponseDTO> getUsuario(String name, Integer page, Integer quantity) {
        return repository.findByNombreStartsWith(name, PageRequest.of(page, quantity))
                .map(u -> u.toResponseDTO());
    }

    public Boolean existeUsuario(String mail) {
        return repository.existsByEmail(mail);
    }

    public UsuarioResponseDTO createUsuario(Usuario usuario) {
        usuario.setContrasenia(encoder.encode(usuario.getContrasenia()));
        return repository.save(usuario).toResponseDTO();
    }

    public void deleteUsuario(Usuario usuario) {
        repository.delete(usuario);
    }

    public UsuarioResponseDTO getByMail(String mail) {
        Usuario u = repository.findByEmail(mail).orElseThrow(() -> new NoSuchElementException("El usuario no existe, refresque la página para actualizar la información"));
        return u.toResponseDTO();
    }

    public Page<UsuarioResponseDTO> getByRole(Role role, Integer page, Integer quantity) {
        return repository.findByType(role, PageRequest.of(page, quantity))
                .map(u -> u.toResponseDTO());
    }

    public Page<UsuarioResponseDTO> getByProtectora(Integer protectora, Integer page, Integer quantity) {
        Protectora prote = Protectora.builder().idProtectora(protectora).build();
        return repository.findByProtectoras(prote, PageRequest.of(page, quantity))
                .map(u -> u.toResponseDTO());
    }

    public UsuarioResponseDTO modificarUsuario(Usuario usuario) {
        usuario.setContrasenia(repository.findById(usuario.getIdPersona()).get().getContrasenia());
        return repository.save(usuario).toResponseDTO();
    }
}
