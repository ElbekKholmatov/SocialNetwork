package com.instagram.instagram.repository;

import com.instagram.instagram.domains.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}