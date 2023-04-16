package com.instagram.instagram.service;


import com.instagram.instagram.domains.HashTag;
import com.instagram.instagram.dto.PostDto;
import com.instagram.instagram.repository.HashTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HashTagService {
    
    private final HashTagRepository hashTagRepository;

    public Optional<HashTag> getHashTag(String hashTag) {
        return hashTagRepository.findByName(hashTag);
    }

    public HashTag save(String hashTag) {
        return hashTagRepository.save(new HashTag(hashTag));
    }
}
