package com.instagram.instagram.controller;


import com.instagram.instagram.config.security.JwtUtils;
import com.instagram.instagram.domains.Saved;
import com.instagram.instagram.service.SavedService;
import lombok.NonNull;
import org.apache.catalina.manager.util.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/saved")
public class SavedController {
//    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    private final SavedService savedService;
    private final JwtUtils jwtUtils;

    public SavedController(SavedService savedService, JwtUtils jwtUtils) {
        this.savedService = savedService;
        this.jwtUtils = jwtUtils;
    }

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
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Saved> deleteUsersSavedPost(@PathVariable Long id){
//        return ResponseEntity.status()
//    }

}
