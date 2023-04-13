package com.instagram.instagram.domains;

import com.instagram.instagram.domains.basic.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(builderMethodName = "childBuilder")
public class Follow extends Auditable<Long>{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id")
    private User following;
    @CreationTimestamp
    @Column(columnDefinition = "timestamp default now()")
    private LocalDateTime createdAt;

    @Builder(builderMethodName = "childBuilder")
    public Follow(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Long createdBy, Long updatedBy, boolean deleted, User follower, User following) {
        super(id, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.follower = follower;
        this.following = following;
    }

}

