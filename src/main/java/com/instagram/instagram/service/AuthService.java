package com.instagram.instagram.service;

import com.instagram.instagram.config.security.JwtUtils;
import com.instagram.instagram.domains.auth.AuthUser;
import com.instagram.instagram.dto.GetTokenDTO;
import com.instagram.instagram.dto.auth.CreateAuthUserDTO;
import com.instagram.instagram.dto.auth.TokenResponse;
import com.instagram.instagram.repository.AuthUserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AuthUserRepository authUserRepository;
    private final JwtUtils jwtUtils;
    private final UserService userService;

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

        Long authId =  authUserRepository.findAuthIdByUsername(authUser.getUsername());

        userService.saveUserByAuthId(authUser.getUsername(), authId);

        return GetTokenDTO.builder()
                .token(tokenResponse.getAccessToken())
                .build();
    }
}
