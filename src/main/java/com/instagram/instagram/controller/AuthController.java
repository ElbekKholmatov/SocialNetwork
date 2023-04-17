package com.instagram.instagram.controller;


import com.instagram.instagram.config.security.UserDetailsService;
import com.instagram.instagram.dto.GenerateTokenDTO;
import com.instagram.instagram.dto.GetTokenDTO;
import com.instagram.instagram.dto.auth.CreateAuthUserDTO;
import com.instagram.instagram.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserDetailsService userDetailsService;
    private final AuthService authService;

    @GetMapping("/token")
    public ResponseEntity<String> getToken(@Valid GenerateTokenDTO dto) {
        return ResponseEntity.ok(userDetailsService.generateToken(dto));
    }

    @PostMapping("/register")
    public ResponseEntity<GetTokenDTO> register(
            @RequestBody CreateAuthUserDTO dto
    ) {
        return ResponseEntity.ok(authService.register(dto));
    }
}
