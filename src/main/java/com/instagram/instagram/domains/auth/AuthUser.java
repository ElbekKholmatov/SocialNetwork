package com.instagram.instagram.domains.auth;

import com.instagram.instagram.domains.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString(callSuper = true)
public class AuthUser extends Auditable<Long> {
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String phoneNumber;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Language language;
    @Enumerated(EnumType.ORDINAL)
    private Active active;
    @Enumerated(EnumType.STRING)
    private Role role;


    public enum Language {
        ENGLISH, UZBEK
    }

    public enum Active {
        BLOCKED, IN_ACTIVE, ACTIVE,
    }

    public enum Role {
        USER, ADMIN
    }

    @Builder(builderMethodName = "childBuilder")
    public AuthUser(Long integer, LocalDateTime createdAt, LocalDateTime updatedAt, Long createdBy, Long updatedBy, boolean deleted, String username, String email, String phoneNumber, String password, Language language, Role role, Active active,String activationCode) {
        super(integer, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.language = language;
        this.role = role;
        this.active = active;
    }
}
