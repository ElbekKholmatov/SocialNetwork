package com.instagram.instagram.repository;

import com.instagram.instagram.domains.basic.Document;
import com.instagram.instagram.domains.basic.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Transactional
    @Query("update User set fullName = coalesce(:_fullName, fullName), bio = coalesce(:_bio, bio), gender = coalesce(:_gender, gender) where authUserId = :_id")
    void update(@Param("_id") Long id,
                @Param("_fullName") String fullName,
                @Param("_bio") String bio,
                @Param("_gender") User.Gender gender);

    @Modifying
    @Transactional
    @Query("update User set picture = ?1 where authUserId = ?2")
    void updateProfilePicture(Document file, Long id);

    @Query("from User where authUserId in ?1")
    List<User> findAllByAuthUserId(List<Long> ids);
}