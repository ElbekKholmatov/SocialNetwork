package com.instagram.instagram.repository;

import com.instagram.instagram.domains.Follow;
import com.instagram.instagram.domains.auth.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    @Modifying
    @Query("delete from Follow f where f.follower.id=?1 and f.following.id=?2")
    long delete(Long from, Long to);
}