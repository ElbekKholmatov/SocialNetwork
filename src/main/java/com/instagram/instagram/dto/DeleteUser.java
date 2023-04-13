package com.instagram.instagram.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.instagram.instagram.domains.auth.AuthUser} entity
 */
@Data
public class DeleteUser implements Serializable {
    private final String username;
}