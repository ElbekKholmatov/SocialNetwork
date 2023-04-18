package com.instagram.instagram.controller;


import com.instagram.instagram.config.security.UserDetailsService;
import com.instagram.instagram.dto.GenerateTokenDTO;
import com.instagram.instagram.dto.auth.TokenRequest;
import com.instagram.instagram.dto.auth.TokenResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserDetailsService userDetailsService;

    public AuthController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/token")
    public ResponseEntity<TokenResponse> getToken(@Valid TokenRequest tokenRequest) {
        return ResponseEntity.ok(userDetailsService.generateToken(tokenRequest));
    }
}
