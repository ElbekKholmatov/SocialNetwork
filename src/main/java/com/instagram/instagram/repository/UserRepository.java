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
    @Query("update User set fullName = coalesce(:_fullName, fullName), bio = coalesce(:_bio, bio), gender = coalesce(:_gender, gender), picture = coalesce(:_picture, picture) where authUserId = :_id")
    void update(@Param("_id") Long id,
                @Param("_fullName") String fullName,
                @Param("_bio") String bio,
                @Param("_gender") User.Gender gender,
                @Param("_picture") Document picture);

    @Query("from User where authUserId in ?1")
    List<User> findAllByAuthUserId(List<Long> ids);
}