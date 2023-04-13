package com.instagram.instagram.repository;

import com.instagram.instagram.domains.basic.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}