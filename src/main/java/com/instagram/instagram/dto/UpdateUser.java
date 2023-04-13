package com.instagram.instagram.dto;

import com.instagram.instagram.domains.auth.AuthUser;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.instagram.instagram.domains.auth.AuthUser} entity
 */
@Data
public class UpdateUser implements Serializable {
    private final Long id;
    private final LocalDateTime updatedAt;
    private final String username;
    private final String email;
    private final String phoneNumber;
    private final AuthUser.Language language;
}