package com.instagram.instagram.service;

import com.instagram.instagram.config.security.SessionUser;
import com.instagram.instagram.domains.Notification;
import com.instagram.instagram.domains.auth.AuthUser;
import com.instagram.instagram.dto.NotificationSentDTO;
import com.instagram.instagram.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

//import static com.instagram.instagram.mapper.NotificationMapper.NOTIFICATION_MAPPER;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final SessionUser sessionUser;
    private final UserService userService;

    public void save(Notification notification) {
        notificationRepository.save(notification);
    }

    public Page<Notification> getMyNotification(Pageable pageable) {
        AuthUser user = userService.getUser(sessionUser.id());

        return notificationRepository.findAllByToUser(user, pageable);

    }

    public Page<Notification> getMySentNotification(Pageable pageable) {
        AuthUser user = userService.getUser(sessionUser.id());
        return notificationRepository.findAllByFromUser(user, pageable);

    }
}
