package com.instagram.instagram.service;

import com.instagram.instagram.domains.Follow;
import com.instagram.instagram.domains.Notification;
import com.instagram.instagram.repository.NotificationRepository;
import com.instagram.instagram.domains.auth.AuthUser;
import com.instagram.instagram.repository.FollowRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FollowService {
    private final FollowRepository followRepository;
    private final NotificationRepository notificationRepository;

    public FollowService(FollowRepository followRepository,
                         NotificationRepository notificationRepository) {
        this.followRepository = followRepository;
        this.notificationRepository = notificationRepository;
    }

    public void save(AuthUser from, AuthUser to) {
        followRepository.save(new Follow(from,to, LocalDateTime.now()));
        notificationRepository.save(new Notification(from.getUsername()+" is following you from "+LocalDateTime.now(),false,from, Notification.NotificationType.FOLLOW));
    }

    public void unfollow(AuthUser from, AuthUser to) {
        followRepository.delete(from.getId(),to.getId());
        notificationRepository.save(new Notification(from.getUsername()+" is unfollowed you at "+LocalDateTime.now(),false,from, Notification.NotificationType.FOLLOW));
    }
}
