package com.instagram.instagram.domains;

import com.instagram.instagram.domains.auth.AuthUser;
import com.instagram.instagram.domains.basic.Post;
import com.instagram.instagram.domains.basic.User;
import jakarta.persistence.Entity;
import lombok.*;
import jakarta.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification extends Auditable<Long>{

    private String message;

    private boolean seen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_id")
    private AuthUser author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_id")
    private AuthUser toUser;

    @Enumerated(EnumType.STRING)
    private NotificationType type;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "story_id")
//    private Story story;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "live_id")
//    private Live live;

    public enum NotificationType {
        LIKE,
        COMMENT,
        FOLLOW,
        DIRECT_MESSAGE,
        TAG,
        MENTION,
        IGTV,
        LIVE,
        STORY
    }

}
