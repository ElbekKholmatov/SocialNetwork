package com.instagram.instagram.service;

import com.instagram.instagram.config.security.SessionUser;
import com.instagram.instagram.domains.HashTag;
import com.instagram.instagram.domains.Notification;
import com.instagram.instagram.domains.auth.AuthUser;
import com.instagram.instagram.domains.basic.Document;
import com.instagram.instagram.domains.basic.Post;
import com.instagram.instagram.domains.basic.User;
import com.instagram.instagram.dto.CreatePostDTO;
import com.instagram.instagram.dto.PostDto;
import com.instagram.instagram.firebase.MediaService;
import com.instagram.instagram.repository.FollowRepository;
import com.instagram.instagram.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.instagram.instagram.mapper.PostMapper.POST_MAPPER;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final DocumentService documentService;
    private final UserService userService;
    private final HashTagService hashTagService;
    private final SessionUser sessionUser;
    private final NotificationService notificationService;
    private final FollowService followService;


    public Optional<Post> getPost(Long id) {
        return postRepository.findById(id);
    }
    public Page<Post> getPostsWithPagination(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Post save(PostDto dto) {
        Long sessionUId = sessionUser.id();
        if (sessionUId ==-1) {
            throw new RuntimeException("User not found");
        }
        CreatePostDTO createPostDTO = new CreatePostDTO(sessionUId,dto.getCaption(),dto.getLocation());
        Post post = POST_MAPPER.toEntity(createPostDTO);
        List<Document> documents = new ArrayList<>();
        dto.getDocuments().forEach(document -> {
            documents.add(documentService.getDocument(document).orElseThrow(
                    () -> new RuntimeException("Document not found")
            ));
        });

        List<AuthUser> mentions = new ArrayList<>();
        dto.getMentions().forEach(mention -> mentions.add(userService.getUser(mention)));

        List<HashTag> hashTags = new ArrayList<>();
        dto.getHashTags().forEach(hashTag -> {
            hashTagService.getHashTag(hashTag).ifPresentOrElse(
                    hashTags::add,
                    () -> hashTags.add(hashTagService.save(hashTag))
            );
        });

        post.setCreatedBy(sessionUId);
        post.setDocuments(documents);
        post.setMentions(mentions);
        post.setHashTags(hashTags);

        log.info("New post was saved by {}.", sessionUId);
        mentions.forEach(authUser -> log.info("This Post mentioned {}",authUser.getId()));
        Notification notification = Notification.childBuilder()
                .message("New post where you are mentioned")
                .from(userService.getUser(sessionUId))
                .to(mentions)
                .type(Notification.NotificationType.MENTION)
                .build();
        notificationService.save(notification);
        List<AuthUser> myFollowers = new ArrayList<>();
        followService.findFollowers(sessionUId).forEach(a -> myFollowers.add(userService.getUser(a)));
        notification = Notification.childBuilder()
                .message("New post from")
                .from(userService.getUser(sessionUId))
                .to(myFollowers)
                .type(Notification.NotificationType.NEW_POST)
                .build();
        notificationService.save(notification);

        return postRepository.save(post);
    }

    public Page<Post> getPostsByMention(String mention, Pageable pageable) {
        AuthUser user = userService.getUser(mention);
        return postRepository.findAllByMentionsUsername(user, pageable);

    }

    public Page<Post> getPostsByHashtag(String hashtag, Pageable pageable) {
        HashTag hashTag1 = hashTagService.getHashTag(hashtag).orElseThrow(() -> new RuntimeException("hash tag not found"));
        return postRepository.findAllByHashTag(hashTag1,pageable);
    }

    public Page<Post> getPostsByUsername(String uname, Pageable pageable) {
        AuthUser user = userService.getUser(uname);
        return postRepository.findAllByUser(user.getId(),pageable);
    }
}

