package com.instagram.instagram.dto;

import com.instagram.instagram.domains.Notification;
import com.instagram.instagram.domains.auth.AuthUser;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NotificationSentDTO {
    String message;
    List<ResiverDTO> toUser;
    Notification.NotificationType type;

    @Getter
    @Setter
    public static class ResiverDTO {
        String username;
        Long id;
    }
}
