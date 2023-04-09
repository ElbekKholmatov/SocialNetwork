package com.instagram.instagram.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString(callSuper = true)
public class Comment extends Auditable<Long, Integer>{
    @Column(nullable = false)
    private Long postId;
    @Column(nullable = false)
    private String message;
    @Column(nullable = false)
    private Integer userId;

    @Builder(builderMethodName = "childBuilder")
    public Comment(Long aLong, LocalDateTime createdAt, LocalDateTime updatedAt, Integer createdBy, Integer updatedBy, boolean deleted, Long postId, String message, Integer userId) {
        super(aLong, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.postId = postId;
        this.message = message;
        this.userId = userId;
    }
}
