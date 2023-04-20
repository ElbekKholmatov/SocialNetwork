package com.instagram.instagram.repository;

import com.instagram.instagram.domains.HashTag;
import com.instagram.instagram.domains.auth.AuthUser;
import com.instagram.instagram.domains.basic.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.createdBy = ?1")
    List<Post> findAllByUserId(Long id);


//    @Query("select p from Post p where p.mentions
    @Query("SELECT p FROM Post p JOIN p.mentions m  WHERE m = :user")
    Page<Post> findAllByMentionsUsername(AuthUser user, Pageable pageable);


    @Query("SELECT p FROM Post p JOIN p.hashTags h  WHERE h = :hashtag")
    Page<Post> findAllByHashTag(HashTag hashtag, Pageable pageable);

    @Query("select p from Post p where p.createdBy = ?1")
    Page<Post> findAllByUser(Long user, Pageable pageable);
}