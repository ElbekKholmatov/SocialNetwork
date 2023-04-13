package com.instagram.instagram.repository;

import com.instagram.instagram.domains.auth.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
}