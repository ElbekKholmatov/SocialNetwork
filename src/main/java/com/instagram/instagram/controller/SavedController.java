package com.instagram.instagram.controller;

import com.instagram.instagram.config.security.SessionUser;
import com.instagram.instagram.domains.Saved;
import com.instagram.instagram.service.SavedService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/saved")
@RequiredArgsConstructor
public class SavedController {
    private final SavedService savedService;
    private final SessionUser sessionUser;



    @GetMapping("/page")
    public ResponseEntity<Page<Saved>> getUserSavedPostsByPage(@RequestParam(name = "size", defaultValue = "3") int size, @RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return ResponseEntity.ok(savedService.getAllSavedMessages(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Saved> getUserSavedPosts(@PathVariable Long id){
        return ResponseEntity.ok(savedService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Saved>> getUsersSavedPostsAll(){
        return ResponseEntity.ok(savedService.getAllSavedMessages());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteUsersSavedPost(@PathVariable @NonNull Long id){

        return ResponseEntity.ok(savedService.deleteSavedPost(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Saved> addUserSavedPost(@RequestBody @NonNull Long  postId){

        return ResponseEntity.ok(savedService.addSavedPost(postId));
    }

}
