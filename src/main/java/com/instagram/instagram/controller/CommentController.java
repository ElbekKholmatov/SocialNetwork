package com.instagram.instagram.controller;

import com.instagram.instagram.domains.Comment;
import com.instagram.instagram.service.CommentService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{id}/all")
    public ResponseEntity<List<Comment>> getAllPostComments(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getPostComments(id));
    }

    @PostMapping("/{id}/add")
    public ResponseEntity<Comment> addPostComment(@PathVariable Long id, String message){
        return ResponseEntity.status(201).body(commentService.addCommentToPost(id, message));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable @NotNull Long commentId){
        return ResponseEntity.ok(commentService.getComment(commentId));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Boolean> deleteCommentById(@PathVariable @NotNull Long commentId){
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }



}
