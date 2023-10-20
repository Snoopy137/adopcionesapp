package com.decssoft.adopciones.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.SEQUENCE;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author mis_p
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
public class Protectora {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "seq", initialValue = 1, allocationSize = 1)
    private Integer idProtectora;
    @NotBlank(message = " Debe ingresar el nombre de la protectora")
    private String nombre;
    @NotBlank(message = " Debe ingresar el télefono de la protectora")
    private String telefono;
    private String direccion;
    @NotBlank(message = " Debe ingresar el email de la protectora")
    private String email;
    private Integer capacidad;
    private String horarioApertura;
    private String horarioCierre;
    @OneToOne
    @Valid
    private Usuario encargado;

}
