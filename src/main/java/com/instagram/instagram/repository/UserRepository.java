package com.instagram.instagram.repository;

import com.instagram.instagram.domains.basic.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}