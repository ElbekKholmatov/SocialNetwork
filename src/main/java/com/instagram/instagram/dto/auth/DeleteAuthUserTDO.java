package com.instagram.instagram.dto.auth;

import lombok.Data;
import org.springdoc.core.annotations.ParameterObject;

import java.io.Serializable;

/**
 * A DTO for the {@link com.instagram.instagram.domains.auth.AuthUser} entity
 */

@ParameterObject
public record DeleteAuthUserTDO(String username) implements Serializable {
}