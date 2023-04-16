package com.instagram.instagram.service;

import com.instagram.instagram.domains.auth.AuthUser;
import com.instagram.instagram.repository.AuthUserRepository;
import com.instagram.instagram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthUserRepository authUserRepository;

    public AuthUser getUser(String username) {
        return authUserRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("Username not found"));
    }
}

