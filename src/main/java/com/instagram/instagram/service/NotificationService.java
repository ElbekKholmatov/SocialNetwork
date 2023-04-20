package com.instagram.instagram.service;

import com.instagram.instagram.domains.Notification;
import com.instagram.instagram.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    public void save(Notification notification) {
        notificationRepository.save(notification);
    }
}
