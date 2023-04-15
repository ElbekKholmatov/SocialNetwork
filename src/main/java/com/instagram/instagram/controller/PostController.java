package com.instagram.instagram.controller;

import com.instagram.instagram.domains.basic.Post;

import com.instagram.instagram.dto.PostDto;
import com.instagram.instagram.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    private final PagedResourcesAssembler<Post> pagedResourcesAssembler;

    private final PostModelAssembler postModelAssembler;

    @GetMapping("/")
    public ResponseEntity<List<Post>> getPosts(){
        return ResponseEntity.ok(
                postService.getPosts()
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id){
        val post = postService.getPost(id).orElseThrow(
                () -> new RuntimeException("Post not found")
        );
        return ResponseEntity.ok(post);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable Long id){
        val posts = postService.getPostsByUserId(id);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/pagination")
    public PagedModel<EntityModel<Post>> getPosts(
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "page", defaultValue = "0") int page
    ){
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> postPage = postService.getPostsWithPagination(pageable);
        return pagedResourcesAssembler.toModel(postPage, postModelAssembler);
    }

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody PostDto postDto){
        val post = postService.save(postDto);
        return ResponseEntity.ok(post);
    }

}
@Component
class PostModelAssembler implements RepresentationModelAssembler<Post, EntityModel<Post>> {

    public EntityModel<Post> toModel(@NonNull Post post) {
        return EntityModel.of(post,
                linkTo(methodOn(PostController.class).getPost(post.getId())).withSelfRel(),
                linkTo(methodOn(PostController.class).getPosts()).withRel("posts"));
    }

    @Override
    public CollectionModel<EntityModel<Post>> toCollectionModel(Iterable<? extends Post> entities) {
        List<EntityModel<Post>> postsEntityModel = new ArrayList<>();
        entities.forEach(post -> postsEntityModel.add(toModel(post)));
        return CollectionModel.of(postsEntityModel,
                linkTo(methodOn(PostController.class).getPosts()).withRel("posts"));
    }

}

