package com.instagram.instagram.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HashTag extends Auditable<Long>{
    @Pattern(regexp = "^#[\\p{L}0-9_]+$", message = "Hashtag is not valid")
    // ^#[\\p{L}0-9_]+$ is used to match hashtags that start with "#" and contain only letters, digits, and underscores.
    @Column(unique = true, nullable = false)
    private String hashTag;

    @Builder(builderMethodName = "childBuilder")
    public HashTag(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Long createdBy, Long updatedBy, boolean deleted, String hashTag) {
        super(id, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.hashTag = hashTag;
    }
}
