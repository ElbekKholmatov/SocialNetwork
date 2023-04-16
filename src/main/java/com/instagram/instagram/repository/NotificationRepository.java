package com.instagram.instagram.repository;

import com.instagram.instagram.domains.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}