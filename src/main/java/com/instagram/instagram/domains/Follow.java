package com.instagram.instagram.domains;

import com.instagram.instagram.domains.auth.AuthUser;
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
public class Follow extends Auditable<Long>{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private AuthUser follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id")
    private AuthUser following;
    @CreationTimestamp
    @Column(columnDefinition = "timestamp default now()")
    private LocalDateTime createdAt;

    @Builder(builderMethodName = "childBuilder")
    public Follow(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Long createdBy, Long updatedBy, boolean deleted, User follower, User following) {
        super(id, createdAt, updatedAt, createdBy, updatedBy, deleted);
    }

}

