package com.decssoft.adopciones.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author mis_p
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @NotBlank(message = "ingrese un email valido")
    @Email(message = "el nombre de usuario debe ser un mail valido")
    private String email;

    @NotEmpty(message = "ingrese una contraseña valida")
    private String password;

}
