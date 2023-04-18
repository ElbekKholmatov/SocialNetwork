package com.instagram.instagram.controller;

import com.instagram.instagram.config.security.SessionUser;
import com.instagram.instagram.domains.basic.Post;
import com.instagram.instagram.dto.PostDto;
import com.instagram.instagram.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final SessionUser sessionUser;

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id){
        val post = postService.getPost(id).orElseThrow(
                () -> new RuntimeException("Post not found")
        );
        return ResponseEntity.ok(post);
    }
    @GetMapping("/mention")
    public Page<Post> getPostsByMention(
            @RequestParam String mention,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "page", defaultValue = "0") int page
    ){
        Pageable pageable = PageRequest.of(page, size);
        return postService.getPostsByMention(mention, pageable);
    }

    @GetMapping("/hashtag")
    public Page<Post> getPostsByHashtag(
            @RequestParam String hashtag,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "page", defaultValue = "0") int page){
        Pageable pageable = PageRequest.of(page, size);
        return postService.getPostsByHashtag(hashtag, pageable);
    }


    @GetMapping("/user/{id}")
    public Page<Post> getPostsByUsername(@PathVariable String uname,
                                        @RequestParam(value = "size", defaultValue = "10") int size,
                                        @RequestParam(value = "page", defaultValue = "0") int page){
        Pageable pageable = PageRequest.of(page, size);
        return postService.getPostsByUsername(uname, pageable);
    }

    @GetMapping("/pagination")
    public Page<Post> getPosts(
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "page", defaultValue = "0") int page
    ){
        Pageable pageable = PageRequest.of(page, size);
        return postService.getPostsWithPagination(pageable);

    }

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody PostDto postDto){
        var post = postService.save(postDto);
        return ResponseEntity.ok(post);
    }

}