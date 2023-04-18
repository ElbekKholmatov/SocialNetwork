package com.instagram.instagram.repository;

import com.instagram.instagram.domains.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c where c.userId=:id")
    Optional<List<Comment>> findAllById(Long id);

    @Transactional
    @Modifying
    @Query("delete from Comment c where c.userId=:userId and c.id=:commentId")
    boolean deleteCommentByUserID(Long userId, Long commentId);
}