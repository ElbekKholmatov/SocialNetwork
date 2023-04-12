package com.instagram.instagram.repos;

import com.instagram.instagram.domains.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository<Comment, ID> extends JpaRepository<Comment, ID> {
}