package com.instagram.instagram.repository;

import com.instagram.instagram.domains.auth.AuthUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    @Query("select a from AuthUser a where upper(a.username) = upper(?1)")
    Optional<AuthUser> findByUsername(String username);

    @Modifying
    @Transactional
    @Query("update AuthUser set deleted = true where username = ?1")
    void setDeleted(String username);

    @Query("select a.id from AuthUser a where a.username = ?1")
    Long findAuthIdByUsername(String username);

    @Modifying
    @Transactional
    @Query("update AuthUser set username = coalesce(:_username,username),email = coalesce(:_email,email),phoneNumber = coalesce(:_phoneNumber,phoneNumber),language = coalesce(:_language,language) where username = :_username")
    void updateAll(@Param("_username") String username,
                   @Param("_phoneNumber") String phoneNumber,
                   @Param("_email") String email,
                   @Param("_language") AuthUser.Language language);

    @Query("select a.username from AuthUser a where a.id = ?1")
    String findAuthUsernameById(Long id);
}