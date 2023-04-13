package com.instagram.instagram.repository;

import com.instagram.instagram.domains.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagRepository extends JpaRepository<HashTag, Long> {
}