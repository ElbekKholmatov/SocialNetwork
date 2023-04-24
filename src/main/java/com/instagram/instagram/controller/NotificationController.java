package com.instagram.instagram.controller;

import com.instagram.instagram.domains.Notification;
import com.instagram.instagram.dto.NotificationGetDTO;
import com.instagram.instagram.dto.NotificationSentDTO;
import com.instagram.instagram.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/my/received/notifications")
    public Page<Notification> getNotification(
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return notificationService.getMyNotification(pageable);
    }

    @GetMapping("/my/sent/notifications")
    public Page<Notification> getSentNotification(

            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return notificationService.getMySentNotification(pageable);
    }
}
