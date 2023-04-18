
package com.instagram.instagram.controller;


import com.instagram.instagram.domains.HashTag;
import com.instagram.instagram.dto.GenerateTokenDTO;
import com.instagram.instagram.repository.HashTagRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/hashCont")
public class HashtagController {
    private final HashTagRepository hashTagRepository;

    public HashtagController(HashTagRepository hashTagRepository) {
        this.hashTagRepository = hashTagRepository;
    }

    @PostMapping("/hashtags")
    public String createHashtag(@RequestBody @Valid HashTag hashtag) {
        hashTagRepository.save(hashtag);
        return "Hashtag created successfully";
    }
}