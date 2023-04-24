package com.instagram.instagram.repository;

import com.instagram.instagram.domains.VerificationCode;
import com.instagram.instagram.domains.auth.AuthUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VerificationRepository extends JpaRepository<VerificationCode, Long> {

    VerificationCode findByEmail(String email);

    @Query("SELECT v FROM VerificationCode v WHERE v.email = :email AND v.deleted = false")
    List<VerificationCode> findAllByEmailAndDeleted(String email);
}