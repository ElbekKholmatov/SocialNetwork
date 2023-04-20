package com.instagram.instagram.dto.auth;

import jakarta.validation.constraints.Pattern;
import org.springdoc.core.annotations.ParameterObject;

import java.io.Serializable;

/**
 * A DTO for the {@link com.instagram.instagram.domains.auth.AuthUser} entity
 */
@ParameterObject
public record CreateAuthUserDTO(
        String username,
        @Pattern(regexp = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6})*$")
        String email,
        @Pattern(regexp = "^\\+998\\d{2}\\d{7}$")
        String phoneNumber,
        String password
) implements Serializable {
}