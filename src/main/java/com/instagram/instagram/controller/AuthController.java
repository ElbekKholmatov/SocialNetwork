package com.instagram.instagram.controller;


import com.instagram.instagram.config.security.JwtUtils;
import com.instagram.instagram.config.security.UserDetailsService;
import com.instagram.instagram.domains.auth.AuthUser;
import com.instagram.instagram.dto.GenerateTokenDTO;
import com.instagram.instagram.dto.GetTokenDTO;
import com.instagram.instagram.dto.auth.CreateAuthUserDTO;
import com.instagram.instagram.dto.auth.UpdateAuthUserDTO;
import com.instagram.instagram.service.AuthService;
import com.instagram.instagram.dto.auth.TokenRequest;
import com.instagram.instagram.dto.auth.TokenResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

//    @PostMapping("/refresh")
//    public ResponseEntity<?> refresh(@CookieValue(name = "refreshToken", required = false) String refreshToken) {
//        if (refreshToken == null) {
//            return ResponseEntity.badRequest().body("Refresh token not provided");
//        }
//        String username = jwt.extractUsername(refreshToken)
//        if (username == null) {
//            return ResponseEntity.badRequest().body("Invalid refresh token");
//        }
//        jwt.getAuthe
//        Authentication authentication = jwtTokenProvider.getAuthentication(username);
//        String newAccessToken = jwtTokenProvider.generateAccessToken(authentication);
//        return ResponseEntity.ok(newAccessToken);
//    }

    @PostMapping("/register")
    public ResponseEntity<GetTokenDTO> register(
            CreateAuthUserDTO dto
    ) {
        return ResponseEntity.ok(authService.register(dto));
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

}
