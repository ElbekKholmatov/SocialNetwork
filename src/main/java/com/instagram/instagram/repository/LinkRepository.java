package com.instagram.instagram.repository;

import com.instagram.instagram.domains.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Long> {
}