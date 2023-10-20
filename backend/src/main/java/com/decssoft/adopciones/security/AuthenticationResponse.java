package com.decssoft.adopciones.security;

import com.decssoft.adopciones.commons.Role;
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
public class AuthenticationResponse {

    private String email;
    private String accessToken;
    private Role type;
}
