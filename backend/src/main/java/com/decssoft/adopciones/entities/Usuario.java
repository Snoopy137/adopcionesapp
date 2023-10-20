package com.decssoft.adopciones.entities;

import com.decssoft.adopciones.DTOs.UsuarioResponseDTO;
import com.decssoft.adopciones.commons.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author mis_p
 */
@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Usuario extends Persona implements UserDetails {

    @NotBlank(message = "Debe ingresar una contraseña")
    private String contrasenia;
    @ManyToOne
    private Protectora protectoras;

    @Enumerated(EnumType.STRING)
    private Role type;

    @Builder
    public Usuario(String contrasenia, Protectora protectoras, Role type, Integer idPersona, String nombre, String telefono, Date fechaDeNacimiento, Long dni, String email) {
        super(idPersona, nombre, telefono, fechaDeNacimiento, dni, email);
        this.contrasenia = contrasenia;
        this.protectoras = protectoras;
        this.type = type;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return type.authorities();
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return this.contrasenia;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public UsuarioResponseDTO toResponseDTO() {
        return UsuarioResponseDTO.builder()
                .dni(this.getDni())
                .email(this.getEmail())
                .fechaDeNacimiento(this.getFechaDeNacimiento())
                .idPersona(this.getIdPersona())
                .nombre(this.getNombre())
                .protectoras(protectoras)
                .telefono(this.getTelefono())
                .type(type)
                .build();
    }

    public Usuario(UsuarioResponseDTO dto) {
        super(dto.getIdPersona(), dto.getNombre(), dto.getTelefono(), dto.getFechaDeNacimiento(), dto.getDni(), dto.getEmail());
        this.contrasenia = null;
        this.protectoras = dto.getProtectoras();
        this.type = dto.getType();
    }
}
