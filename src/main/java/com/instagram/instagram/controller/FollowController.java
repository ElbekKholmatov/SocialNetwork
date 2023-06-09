package com.instagram.instagram.controller;



import com.instagram.instagram.criteria.FollowersCriteria;
import com.instagram.instagram.domains.auth.AuthUser;
import com.instagram.instagram.domains.basic.User;
import com.instagram.instagram.dto.FollowDTO;
import com.instagram.instagram.service.FollowService;
import com.instagram.instagram.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/follows")
@RequiredArgsConstructor
public class FollowController {

    private final UserService userService;
    private final FollowService followService;

    @PostMapping("/follow")
    public ResponseEntity<Void> follow(@Valid FollowDTO followDTO) {
        AuthUser from = userService.getUser(followDTO.from());
        AuthUser to = userService.getUser(followDTO.to());
        followService.save(from, to);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/unfollow")
    public ResponseEntity<Void> unfollow(@Valid FollowDTO followDTO) {
        AuthUser from = userService.getUser(followDTO.from());
        AuthUser to = userService.getUser(followDTO.to());
        followService.unfollow(from, to);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/followers")
    public ResponseEntity<List<User>> followers(@Valid @RequestBody FollowersCriteria criteria) {
        return ResponseEntity.ok(followService.findAll(criteria));
    }
}
