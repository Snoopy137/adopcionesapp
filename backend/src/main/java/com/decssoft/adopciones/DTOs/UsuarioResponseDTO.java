package com.decssoft.adopciones.DTOs;

import com.decssoft.adopciones.commons.Role;
import com.decssoft.adopciones.entities.Protectora;
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
public class UsuarioResponseDTO {

    private Integer idPersona;
    private String nombre;
    private String telefono;
    private Date fechaDeNacimiento;
    private Long dni;
    private String email;
    private Protectora protectoras;
    private Role type;
}
