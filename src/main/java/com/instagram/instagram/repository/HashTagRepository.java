package com.instagram.instagram.repository;

import com.instagram.instagram.domains.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface HashTagRepository extends JpaRepository<HashTag, Long> {
    @Query("select h from HashTag h where h.hashTag = ?1")
    Optional<HashTag> findByName(String hashTag);
}