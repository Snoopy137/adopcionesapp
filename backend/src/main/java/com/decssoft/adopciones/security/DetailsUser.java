package com.decssoft.adopciones.security;

import com.decssoft.adopciones.entities.Usuario;
import com.decssoft.adopciones.repositories.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author mis_p
 */
@Service
@Slf4j
public class DetailsUser implements UserDetailsService {

    @Autowired
    UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Usuario usuario = repository.findByEmail(username).get();
        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDetails user = User.withUsername(usuario.getEmail()).password(usuario.getPassword()).authorities(usuario.getAuthorities()).build();
        return user;
    }

}
