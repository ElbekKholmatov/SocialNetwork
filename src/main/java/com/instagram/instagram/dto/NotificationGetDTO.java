package com.instagram.instagram.dto;

import com.instagram.instagram.domains.Notification;

import java.util.List;

public class NotificationGetDTO {
    String message;

    String fromUsername;
    List<UserDTO> toUser;
    Notification.NotificationType type;

    private class UserDTO {
        Long id;
        String username;
    }


}
