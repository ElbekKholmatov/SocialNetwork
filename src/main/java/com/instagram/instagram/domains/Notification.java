package com.instagram.instagram.domains;

import com.instagram.instagram.domains.auth.AuthUser;
import com.instagram.instagram.domains.basic.Post;
import com.instagram.instagram.domains.basic.User;
import jakarta.persistence.Entity;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;


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
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AuthUser> toUser;
    @Enumerated(EnumType.STRING)
    private NotificationType type;

    public enum NotificationType {
        LIKE,
        COMMENT,
        FOLLOW,
        DIRECT_MESSAGE,
        TAG,
        MENTION,
        IGTV,
        LIVE,
        STORY,
        NEW_POST
    }

    @Builder(builderMethodName = "childBuilder")
    public Notification(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Long createdBy, Long updatedBy, boolean deleted,String message,boolean seen,AuthUser from,List<AuthUser> to,NotificationType type){
        super(id, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.message = message;
        this.seen = seen;
        this.author = from;
        this.toUser = to;
        this.type = type;

    }
}
