package com.instagram.instagram;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


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
//                    .username("jason")
//                    .email("ex@gmail.com")
//                    .phoneNumber("010-1234-5678")
//                    .password(passwordEncoder.encode("123"))
//                    .language(AuthUser.Language.ENGLISH)
//                    .role(AuthUser.Role.USER)
//                    .build();
//            authUserRepository.save(authUser);
//        };
//    }



}
