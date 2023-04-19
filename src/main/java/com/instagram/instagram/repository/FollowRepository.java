package com.instagram.instagram.repository;

import com.instagram.instagram.domains.Follow;
import com.instagram.instagram.domains.auth.AuthUser;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    @Modifying
    @Transactional
    @Query("delete from Follow f where f.follower.id=?1 and f.following.id=?2")
    void delete(Long from, Long to);

//    @Query(value = "from Follow f where f.following.username=?1")
    Page<Follow> findAllByFollowing(AuthUser following, Pageable pageable);
}