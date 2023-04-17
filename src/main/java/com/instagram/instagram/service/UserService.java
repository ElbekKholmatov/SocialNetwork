package com.instagram.instagram.service;

import com.instagram.instagram.domains.auth.AuthUser;
import com.instagram.instagram.domains.basic.User;
import com.instagram.instagram.repository.AuthUserRepository;
import com.instagram.instagram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthUserRepository authUserRepository;

    public Optional<AuthUser> getUser(String username) {
        return authUserRepository.findByUsername(username);
    }

    public void saveUserByAuthId(String username, Long authId) {
        User user = User.builder()
                .authUserId(authId)
                .fullName(username)
                .bio("")
                .gender(User.Gender.NOT_GIVEN)
                .picture(null)
                .build();

        System.out.println("User Service    34:  =>  user saving process      ");
        System.out.println(user.toString());
        userRepository.save(user);
    }
}

