package com.instagram.instagram.config.security;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Social Network API")
                        .description("This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3.")
                        .version("${api.version}")
                        .contact(new Contact()
                                .name("Excezours")
                                .email("exolmatov99@gmail.com")
                                .url("redirect:/sheengo.live"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org"))
                        .termsOfService("http://swagger.io/terms/"))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"))
                .servers(List.of(
                        new Server().url("http://localhost:8081").description("Production-Server"),
                        new Server().url("http://localhost:9090").description("Test-Server")
                ))
                .addSecurityItem(new SecurityRequirement()
                        .addList("bearerAuth")
                        .addList("basicAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .name("bearerAuth")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT"))
                        .addSecuritySchemes("basicAuth",
                                new SecurityScheme()
                                        .name("basicAuth")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("basic"))
                );

    }

    @Bean
    public GroupedOpenApi all() {
        return GroupedOpenApi.builder()
                .group("all")
                .pathsToMatch(
                        "/**"
                )
                .build();
    }
    @Bean
    public GroupedOpenApi user() {
        return GroupedOpenApi.builder()
                .group("user")
                .pathsToMatch(
                        "/api/v1/auth/**"
                )
                .build();
    }

    @Bean
    public GroupedOpenApi comment() {
        return GroupedOpenApi.builder()
                .group("comment")
                .pathsToMatch(
                        "/api/v1/comment/**"
                )
                .build();
    }

    @Bean
    public GroupedOpenApi document() {
        return GroupedOpenApi.builder()
                .group("document")
                .pathsToMatch(
                        "/api/v1/document/**"
                )
                .build();
    }

    @Bean
    public GroupedOpenApi follow() {
        return GroupedOpenApi.builder()
                .group("follow")
                .pathsToMatch(
                        "/api/v1/follows/**"
                )
                .build();
    }



    @Bean
    public GroupedOpenApi post() {
        return GroupedOpenApi.builder()
                .group("post")
                .pathsToMatch(
                        "/api/v1/post/**"
                )
                .build();
    }

    @Bean
    public GroupedOpenApi saved() {
        return GroupedOpenApi.builder()
                .group("saved")
                .pathsToMatch(
                        "/api/v1/saved/**"
                )
                .build();
    }

    @Bean
    public GroupedOpenApi location() {
        return GroupedOpenApi.builder()
                .group("location")
                .pathsToMatch(
                        "/api/v1/location/**"
                )
                .build();
    }

    @Bean
    public GroupedOpenApi notification() {
        return GroupedOpenApi.builder()
                .group("notification")
                .pathsToMatch(
                        "/api/v1/notification/**"
                )
                .build();
    }

    @Bean
    public GroupedOpenApi mailing() {
        return GroupedOpenApi.builder()
                .group("mailing")
                .pathsToMatch(
                        "/api/v1/mail/**"
                )
                .build();
    }
}

