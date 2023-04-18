package com.instagram.instagram.service;

import com.instagram.instagram.config.security.JwtUtils;
import com.instagram.instagram.domains.auth.AuthUser;
import com.instagram.instagram.dto.GetTokenDTO;
import com.instagram.instagram.dto.auth.CreateAuthUserDTO;
import com.instagram.instagram.dto.auth.UpdateAuthUserDTO;
import com.instagram.instagram.dto.auth.TokenResponse;
import com.instagram.instagram.repository.AuthUserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AuthUserRepository authUserRepository;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public GetTokenDTO register(CreateAuthUserDTO dto) {
        AuthUser authUser = new AuthUser();
        authUser.setUsername(dto.getUsername());
        authUser.setEmail(dto.getEmail());
        authUser.setPhoneNumber(dto.getPhoneNumber());
        authUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        authUser.setLanguage(AuthUser.Language.UZBEK);
        authUser.setRole(AuthUser.Role.USER);

        authUserRepository.save(authUser);
        TokenResponse tokenResponse = jwtUtils.generateToken(authUser.getUsername());

        Long authId = authUserRepository.findAuthIdByUsername(authUser.getUsername());

        userService.saveUserByAuthId(authUser.getUsername(), authId);

        return GetTokenDTO.builder()
                .token(tokenResponse.getAccessToken())
                .build();
    }

    public GetTokenDTO login(CreateAuthUserDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.username(),
                        dto.password()
                )
        );
        AuthUser user = authUserRepository.findByUsername(dto.username())
                .orElseThrow();
        String jwtToken = jwtUtils.generateToken(user);
        return GetTokenDTO.builder()
                .token(jwtToken)
                .build();
    }

    public GetTokenDTO update(@NonNull UpdateAuthUserDTO dto) {
        AuthUser authUser = authUserRepository.findByUsername(dto.username())
                .orElseThrow(() -> new UsernameNotFoundException("Auth User not found with username: " + dto.username()));
//        AuthUser authUser1 = new AuthUser();
        authUser.setUsername(dto.username());
        authUser.setEmail(dto.email());
        authUser.setPhoneNumber(dto.phoneNumber());
        authUser.setUpdatedAt(dto.updatedAt());
        authUser.setLanguage(AuthUser.Language.UZBEK);
        authUser.setRole(AuthUser.Role.USER);
        authUserRepository.updateAll(dto.username(), dto.phoneNumber(), dto.email(), dto.language());
        String jwtToken = jwtUtils.generateToken(authUserRepository.findByUsername(dto.username()).orElseThrow());

        return GetTokenDTO.builder()
                .token(jwtToken)
                .build();
    }

    public void deleteAccount(String username) {
        AuthUser authUser = authUserRepository.findByUsername(username)
                .orElseThrow();
        authUserRepository.setDeleted(username);
    }

    public AuthUser findInfoByUsername(String username) {
        return authUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Auth User not found with username: " + username));
    }
}
