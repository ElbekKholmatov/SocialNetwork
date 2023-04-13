package com.instagram.instagram.repository;

import com.instagram.instagram.domains.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}