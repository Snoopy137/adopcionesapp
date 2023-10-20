package com.decssoft.adopciones.entities;

import com.decssoft.adopciones.commons.Estado;
import com.decssoft.adopciones.commons.Genero;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author mis_p
 */
@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Gato extends Animal {

    private Boolean aptoDepto;

    @Builder
    public Gato(Boolean aptoDepto, Integer animalid, String raza, Integer edad, String nombre, Genero genero, Boolean castrado, Estado estado, Adoptante adoptante, Transito transito, Protectora protectora, String foto) {
        super(animalid, raza, edad, nombre, genero, castrado, estado, adoptante, transito, protectora, foto);
        this.aptoDepto = aptoDepto;
    }

}
