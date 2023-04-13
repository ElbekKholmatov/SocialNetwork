package com.instagram.instagram.dto.auth;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.instagram.instagram.domains.auth.AuthUser} entity
 */
@Data
public class CreateUser implements Serializable {
    private final String username;
    private final String email;
    private final String phoneNumber;
    private final String password;
}