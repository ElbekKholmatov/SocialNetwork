package com.instagram.instagram.controller;

import com.instagram.instagram.criteria.FollowersCriteria;
import com.instagram.instagram.domains.Follow;
import com.instagram.instagram.domains.auth.AuthUser;
import com.instagram.instagram.domains.basic.User;
import com.instagram.instagram.dto.FollowDTO;
import com.instagram.instagram.service.FollowService;
import com.instagram.instagram.service.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class FollowController {

    private final UserService userService;
    private final FollowService followService;

    @PostMapping("/follow")
    public ResponseEntity<Void> follow(@Valid FollowDTO followDTO) {
        AuthUser from = userService.getUser(followDTO.from());
        AuthUser to = userService.getUser(followDTO.to());
        followService.save(from, to);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/unfollow")
    public ResponseEntity<Void> unfollow(@Valid FollowDTO followDTO) {
        AuthUser from = userService.getUser(followDTO.from());
        AuthUser to = userService.getUser(followDTO.to());
        followService.unfollow(from, to);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/followers")
    public ResponseEntity<List<User>> followers(@RequestBody FollowersCriteria criteria) {
        return ResponseEntity.ok(followService.findAll(criteria));
    }
}
