package com.instagram.instagram.service;

import com.instagram.instagram.config.security.SessionUser;
import com.instagram.instagram.domains.Saved;
import com.instagram.instagram.domains.basic.Post;
import com.instagram.instagram.domains.basic.User;
import com.instagram.instagram.repository.PostRepository;
import com.instagram.instagram.repository.UserRepository;
import com.instagram.instagram.repository.savedPostsRepo.SavedPagingAndSortingRepository;
import com.instagram.instagram.repository.savedPostsRepo.SavedRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SavedService {
    private final SavedRepository savedRepository;
    private final SavedPagingAndSortingRepository savedPagingAndSortingRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final SessionUser sessionUser;


    public Page<Saved> getAllSavedMessages(Pageable pageable) {
        return savedPagingAndSortingRepository.findAllByUsername(pageable, sessionUser.id());
    }
    public List<Saved> getAllSavedMessages() {
        return savedRepository.findAllUsersPosts(sessionUser.id());
    }
    public Saved getById(@NotNull Long id) {
     return savedRepository.findById(id).orElseThrow(()->new RuntimeException("Saved Post Not Found!"));
    }

    public boolean deleteSavedPost(@NotNull Long postId) {

        return savedRepository.deleteAllByIdAndUsername(postId,sessionUser.id());
    }

    public Saved addSavedPost(Long postId) {
        Long id = sessionUser.id();
        Post post = postRepository.findById(postId).orElseThrow(()-> new RuntimeException("Post Not Found"));
        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User Not Found!"));
        return savedRepository.save(Saved.childBuilder()
                .post(post)
                .user(user)
                .createdBy(id)
                .updatedBy(id)
                .build());
    }
}
