package com.decssoft.adopciones.commons;

import static com.decssoft.adopciones.commons.Permission.ENCARGADO_CREATE;
import static com.decssoft.adopciones.commons.Permission.ENCARGADO_DELETE;
import static com.decssoft.adopciones.commons.Permission.ENCARGADO_READ;
import static com.decssoft.adopciones.commons.Permission.ENCARGADO_UPDATE;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 *
 * @author mis_p
 */
@RequiredArgsConstructor
public enum Role {

    TRANSITO(Collections.emptySet()),
    ENCARGADO(Set.of(
            ENCARGADO_READ,
            ENCARGADO_UPDATE,
            ENCARGADO_DELETE,
            ENCARGADO_CREATE)),
    COLABORADOR(Collections.emptySet()),
    ADMINISTRADOR(Collections.emptySet());

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> authorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
