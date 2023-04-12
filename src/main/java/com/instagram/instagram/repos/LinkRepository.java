package com.instagram.instagram.repos;

import com.instagram.instagram.domains.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository<Link, ID> extends JpaRepository<Link, ID> {
}