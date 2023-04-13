package com.instagram.instagram.repository;

import com.instagram.instagram.domains.Saved;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavedRepository extends JpaRepository<Saved, Long> {
}