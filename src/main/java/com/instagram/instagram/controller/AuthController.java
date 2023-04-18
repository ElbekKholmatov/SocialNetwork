package com.instagram.instagram.controller;


import com.instagram.instagram.config.security.UserDetailsService;
import com.instagram.instagram.dto.GenerateTokenDTO;
import com.instagram.instagram.dto.GetTokenDTO;
import com.instagram.instagram.dto.auth.CreateAuthUserDTO;
import com.instagram.instagram.service.AuthService;
import com.instagram.instagram.dto.auth.TokenRequest;
import com.instagram.instagram.dto.auth.TokenResponse;
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
    public ResponseEntity<TokenResponse> getToken(@Valid TokenRequest tokenRequest) {
        return ResponseEntity.ok(userDetailsService.generateToken(tokenRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<GetTokenDTO> register(
            @RequestBody CreateAuthUserDTO dto
    ) {
        return ResponseEntity.ok(authService.register(dto));
    }
}
