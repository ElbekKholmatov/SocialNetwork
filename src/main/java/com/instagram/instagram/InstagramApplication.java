package com.instagram.instagram;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.instagram.instagram.domains.auth.AuthUser;
import com.instagram.instagram.repository.AuthUserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


@SpringBootApplication
@OpenAPIDefinition
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class InstagramApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(InstagramApplication.class, args);
    }


//    @Bean
//    ApplicationRunner runner(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder) {
//        return args -> {
//            AuthUser authUser = AuthUser.childBuilder()
//                    .username("john")
//                    .email("jlkesh@gmail.com")
//                    .phoneNumber("+998971112233")
//                    .password(passwordEncoder.encode("123"))
//                    .language(AuthUser.Language.ENGLISH)
//                    .role(AuthUser.Role.USER)
//                    .active(AuthUser.Active.ACTIVE)
//                    .build();
//            authUserRepository.save(authUser);
//        };
//    }



}
