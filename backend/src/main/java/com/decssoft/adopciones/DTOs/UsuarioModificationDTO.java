package com.decssoft.adopciones.DTOs;

import com.decssoft.adopciones.commons.Role;
import com.decssoft.adopciones.entities.Protectora;
import com.decssoft.adopciones.entities.Usuario;
import com.decssoft.adopciones.exceptions.Age;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author mis_p
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UsuarioModificationDTO {

    private Integer idPersona;
    @NotBlank(message = "Debe ingresar um nombre y un apellido")
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
    private Protectora protectoras;
    private Role type;

    public Usuario toUsuario() {
        return Usuario.builder()
                .dni(dni)
                .email(email)
                .fechaDeNacimiento(fechaDeNacimiento)
                .idPersona(idPersona)
                .nombre(nombre)
                .protectoras(protectoras)
                .telefono(telefono)
                .type(type)
                .build();
    }
}
