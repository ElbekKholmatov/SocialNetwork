package com.instagram.instagram.controller;

import com.instagram.instagram.config.security.UserDetailsService;
import com.instagram.instagram.domains.auth.AuthUser;
import com.instagram.instagram.dto.GetTokenDTO;
import com.instagram.instagram.dto.auth.*;
import com.instagram.instagram.service.AuthService;
import com.instagram.instagram.service.MailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserDetailsService userDetailsService;
    private final AuthService authService;
    private final MailService mailService;


    @GetMapping("/token")
    public ResponseEntity<TokenResponse> getToken(@Valid TokenRequest tokenRequest) {
        return ResponseEntity.ok(userDetailsService.generateToken(tokenRequest));
    }


    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@Valid RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(userDetailsService.refreshToken(refreshTokenRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            CreateAuthUserDTO dto
    ) {
         authService.register(dto);
            return ResponseEntity.ok("User registered successfully\n" +
                    "Please check your email for activation link");
    }

    @PostMapping("/login")
    public ResponseEntity<GetTokenDTO> login(
           CreateAuthUserDTO request
    ) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PutMapping("/update")
    public ResponseEntity<GetTokenDTO> update(
            UpdateAuthUserDTO dto
    ) {
        return ResponseEntity.ok(authService.update(dto));
    }

    @DeleteMapping("/deleteAccount")
//    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> deleteAccount(
            @Valid String username
    ) {
        authService.deleteAccount(username);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getInfo")
    public ResponseEntity<AuthUser> getInfo(
            @Valid String username
    ) {
        return ResponseEntity.ok(authService.findInfoByUsername(username));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/activate")
    public ResponseEntity<GetTokenDTO> activate(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String otp){

        GetTokenDTO activate = authService.activate(username, email, otp);
        return ResponseEntity.ok(activate);
    }

}
