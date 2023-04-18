package com.instagram.instagram.domains;

import com.instagram.instagram.domains.basic.Post;
import com.instagram.instagram.domains.basic.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "saved")
@AllArgsConstructor
public class Saved extends Auditable<Long>{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder(builderMethodName = "childBuilder")
    public Saved(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Long createdBy, Long updatedBy, boolean deleted, User user, Post post) {
        super(id, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.user = user;
        this.post = post;
    }

}
