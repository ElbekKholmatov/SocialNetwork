package com.instagram.instagram.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Comment extends Auditable<Long>{
    @Column(nullable = false)
    private Long postId;
    @Column(nullable = false)
    private String message;
    @Column(nullable = false)
    private Long userId;

    @Builder(builderMethodName = "childBuilder")
    public Comment(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Long createdBy, Long updatedBy, boolean deleted, Long postId, String message, Long userId) {
        super(id, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.postId = postId;
        this.message = message;
        this.userId = userId;
    }
}
