package com.decssoft.adopciones.entities;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author mis_p
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Adoptante extends Persona {

    private String picture;
    private Boolean listaNegra;
    private String motivo;
    @NotBlank(message = "Debe ingresar una dirección")
    private String direccion;

    @Builder
    public Adoptante(String picture, Boolean listaNegra, String motivo, String direccion, Integer idPersona, String nombre, String telefono, Date fechaDeNacimiento, Long dni, String email) {
        super(idPersona, nombre, telefono, fechaDeNacimiento, dni, email);
        this.picture = picture;
        this.listaNegra = listaNegra;
        this.motivo = motivo;
        this.direccion = direccion;
    }
}
