package com.instagram.instagram.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(
        @NotBlank String refreshToken) {
}
