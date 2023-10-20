package com.decssoft.adopciones.entities;

import com.decssoft.adopciones.exceptions.Age;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.SEQUENCE;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.util.Date;
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
public abstract class Persona {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "personaGen")
    @SequenceGenerator(name = "personaGen", sequenceName = "seq", initialValue = 1, allocationSize = 1)
    private Integer idPersona;
    @NotBlank(message = "Debe ingresar su nombre y apellido")
    private String nombre;
    @NotBlank(message = "Debe ingresar su teléfono")
    private String telefono;
    @Past(message = "La fecha no puede ser futura")
    @Age(message = "Debe ser mayor de edad para registrarse")
    private Date fechaDeNacimiento;
    @NotNull(message = "Debe ingresar su DNI")
    private Long dni;
    @Email(message = "Debe ingresar su mail")
    @NotEmpty(message = "Debe ingresar su mail")
    private String email;
}
