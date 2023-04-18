package com.instagram.instagram.repository;

import com.instagram.instagram.domains.Saved;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SavedRepository extends JpaRepository<Saved, Long> {
    @Transactional
    @Modifying
    @Query("delete from Saved s where s.post.id=:postId and s.user.authUserId=:userId")
    boolean deleteAllByIdAndUsername(Long postId,Long userId);
    @Query("select s from Saved s where s.user.authUserId=:id")
    List<Saved> findAllUsersPosts(Long id);

    @Query("select s from Saved s where s.user.authUserId=:userId")
    Page<Saved> findAllByUsername(Pageable pageable, Long userId);
}