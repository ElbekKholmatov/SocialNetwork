package com.instagram.instagram.repository;

import com.instagram.instagram.domains.Notification;
import com.instagram.instagram.domains.auth.AuthUser;
import com.instagram.instagram.dto.NotificationGetDTO;
import com.instagram.instagram.dto.NotificationSentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("select n from Notification n where n.author = ?1")
    Page<Notification> findAllByFromUser(AuthUser user, Pageable pageable);

    @Query("select n from Notification n where ?1 member of n.toUser")
    Page<Notification> findAllByToUser(AuthUser user, Pageable pageable);
}