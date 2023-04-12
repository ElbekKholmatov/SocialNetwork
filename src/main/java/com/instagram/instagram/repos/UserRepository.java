package com.instagram.instagram.repos;

import com.instagram.instagram.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}