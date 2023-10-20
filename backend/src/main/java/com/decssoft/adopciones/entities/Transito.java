package com.decssoft.adopciones.entities;

import com.decssoft.adopciones.commons.Role;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author mis_p
 */
@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Transito extends Usuario {

    private Boolean disponible;
    @NotBlank(message = "debe ingresar un domicilio")
    private String domicilio;

    public Transito(Boolean disponible, String domicilio, String contrasenia, Protectora protectoras, Role type, Integer idPersona, String nombre, String telefono, Date fechaDeNacimiento, Long dni, String email) {
        super(contrasenia, protectoras, type, idPersona, nombre, telefono, fechaDeNacimiento, dni, email);
        this.disponible = disponible;
        this.domicilio = domicilio;
    }

}
