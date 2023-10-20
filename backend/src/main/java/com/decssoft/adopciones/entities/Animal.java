package com.decssoft.adopciones.entities;

import com.decssoft.adopciones.commons.Estado;
import com.decssoft.adopciones.commons.Genero;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.SEQUENCE;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author mis_p
 */
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Animal {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "personaGen")
    @SequenceGenerator(name = "personaGen", sequenceName = "seq", initialValue = 1, allocationSize = 1)
    private Integer animalid;
    private String raza;
    @NotNull(message = "Ingrese una edad")
    @Min(value = 1, message = "la edad no puede ser menor a 1")
    @Max(value = 25, message = "la edad no puede ser mayor a 25")
    private Integer edad;
    @NotBlank(message = "el nombre no puede estar vacio")
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    private Boolean castrado;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @ManyToOne
    private Adoptante adoptante;
    @ManyToOne
    private Transito transito;
    @ManyToOne
    @JoinColumn(name = "protectora_id_protectora")
    private Protectora protectora;
    private String foto;
}
