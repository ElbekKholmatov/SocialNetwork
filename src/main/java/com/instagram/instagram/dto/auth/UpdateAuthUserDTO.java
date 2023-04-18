package com.instagram.instagram.dto.auth;

import com.instagram.instagram.domains.auth.AuthUser;
import lombok.Data;
import org.springdoc.core.annotations.ParameterObject;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link AuthUser} entity
 */
@ParameterObject
public record UpdateAuthUserDTO(Long id, LocalDateTime updatedAt, String username, String email, String phoneNumber,
                                AuthUser.Language language) implements Serializable {
}