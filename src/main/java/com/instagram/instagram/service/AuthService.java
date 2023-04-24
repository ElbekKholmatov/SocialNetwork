package com.instagram.instagram.service;

import ch.qos.logback.core.testUtil.RandomUtil;
import com.instagram.instagram.config.security.JwtUtils;
import com.instagram.instagram.domains.VerificationCode;
import com.instagram.instagram.domains.auth.AuthUser;
import com.instagram.instagram.dto.GetTokenDTO;
import com.instagram.instagram.dto.auth.CreateAuthUserDTO;
import com.instagram.instagram.dto.auth.UpdateAuthUserDTO;
import com.instagram.instagram.dto.auth.TokenResponse;
import com.instagram.instagram.repository.AuthUserRepository;
import com.instagram.instagram.repository.VerificationRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Lazy
@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AuthUserRepository authUserRepository;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final MailService mailService;
    private final VerificationRepository verificationRepository;

    public void register(CreateAuthUserDTO dto) {


        if (!dto.password().equals(dto.confirmPassword())) {
            throw new RuntimeException("Passwords are not equal");
        }
        AuthUser authUser = AuthUser.childBuilder()
                .username(dto.username())
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .password(passwordEncoder.encode(dto.password()))
                .language(AuthUser.Language.UZBEK)
                .role(AuthUser.Role.USER)
                .active(AuthUser.Active.IN_ACTIVE)
                .build();

        authUserRepository.save(authUser);

        mailService.sendActivationLink(dto.username(), dto.email());

        Long authId = authUserRepository.findAuthIdByUsername(authUser.getUsername());

        userService.saveUserByAuthId(authUser.getUsername(), authId);
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
        TokenResponse tokenResponse = jwtUtils.generateToken(user.getUsername());
        return GetTokenDTO.builder()
                .token(tokenResponse.getAccessToken())
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
        TokenResponse tokenResponse = jwtUtils.generateToken(authUserRepository.findByUsername(dto.username()).get().getUsername());

        return GetTokenDTO.builder()
                .token(tokenResponse.getAccessToken())
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

    public GetTokenDTO activate(String username, String email, String otp) {
        VerificationCode verificationCode = verificationRepository.findByEmail(email);
        if (!verificationCode.getExpiryDate().before(new Date()))
            throw new RuntimeException("OTP expired");
        if (!verificationCode.getCode().equals(otp))
            throw new RuntimeException("OTP is not valid");
        AuthUser infoByUsername = findInfoByUsername(username);
        infoByUsername.setActive(AuthUser.Active.ACTIVE);
        TokenResponse tokenResponse = jwtUtils.generateToken(username);
        return GetTokenDTO.builder()
                .token(tokenResponse.getAccessToken())
                .build();
    }
}
