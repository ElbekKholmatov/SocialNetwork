package com.instagram.instagram.controller;


import com.instagram.instagram.config.UserDetailsService;
import com.instagram.instagram.dto.GenerateTokenDTO;
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
    public ResponseEntity<String> getToken(@Valid GenerateTokenDTO dto) {
        return ResponseEntity.ok(userDetailsService.generateToken(dto));
    }
}
