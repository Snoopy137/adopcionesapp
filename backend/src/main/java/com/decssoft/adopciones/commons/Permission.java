package com.decssoft.adopciones.commons;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author mis_p
 */
@RequiredArgsConstructor
public enum Permission {
    @JsonIgnore
    ENCARGADO_READ("encargado:read"),
    @JsonIgnore
    ENCARGADO_UPDATE("encargado:update"),
    @JsonIgnore
    ENCARGADO_CREATE("encargado:create"),
    @JsonIgnore
    ENCARGADO_DELETE("encargado:delete");

    @Getter
    private final String permission;
}
