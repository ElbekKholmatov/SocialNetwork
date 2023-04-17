package com.instagram.instagram.service;

import com.instagram.instagram.config.security.SessionUser;
import com.instagram.instagram.domains.Comment;
import com.instagram.instagram.repository.CommentRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final SessionUser sessionUser;

    public List<Comment> getPostComments(@NotNull Long id) {
     return commentRepository.findAllById(id).orElseThrow(()->new RuntimeException("Comments not found exception!"));
    }

    public Comment addCommentToPost(@NotNull Long id, String message) {
        return commentRepository.save(Comment.childBuilder()
                .postId(id)
                .createdBy(sessionUser.id())
                .updatedBy(sessionUser.id())
                .message(message)
                .build());
    }

    public Comment getComment(@NotNull Long commentId) {
     return commentRepository.findById(commentId).orElseThrow(()->new RuntimeException("Comment Not Found!"));
    }

    public boolean deleteComment(Long commentId) {
        return commentRepository.deleteCommentByUserID(commentId, sessionUser.id());
    }



}
