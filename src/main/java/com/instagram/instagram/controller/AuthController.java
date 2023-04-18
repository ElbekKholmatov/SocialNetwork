package com.instagram.instagram.controller;


import com.instagram.instagram.config.security.JwtUtils;
import com.instagram.instagram.config.security.UserDetailsService;
import com.instagram.instagram.domains.auth.AuthUser;
import com.instagram.instagram.dto.GetTokenDTO;
import com.instagram.instagram.dto.auth.CreateAuthUserDTO;
import com.instagram.instagram.dto.auth.TokenRequest;
import com.instagram.instagram.dto.auth.TokenResponse;
import com.instagram.instagram.dto.auth.UpdateAuthUserDTO;
import com.instagram.instagram.service.AuthService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserDetailsService userDetailsService;
    private final AuthService authService;
    private final JwtUtils jwt;

    @GetMapping("/token")
    public ResponseEntity<TokenResponse> getToken(@Valid TokenRequest tokenRequest) {
        return ResponseEntity.ok(userDetailsService.generateToken(tokenRequest));
    }


    @PostMapping("/register")
    public ResponseEntity<GetTokenDTO> register(
            @io.swagger.v3.oas.annotations.parameters.RequestBody @NonNull CreateAuthUserDTO dto
    ) {
        System.out.println(" ---------------------------------------------  dto = " + dto);
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<GetTokenDTO> login(
            @io.swagger.v3.oas.annotations.parameters.RequestBody CreateAuthUserDTO request
    ) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PutMapping("/update")
    public ResponseEntity<GetTokenDTO> update(@io.swagger.v3.oas.annotations.parameters.RequestBody UpdateAuthUserDTO dto) {
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

}
