package com.instagram.instagram.domains;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(builderMethodName = "childBuilder")
public class HashTag extends Auditable<Long>{
    @Column(unique = true,nullable = false)
    private String hashTag;

    @Builder(builderMethodName = "childBuilder")
    public HashTag(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Long createdBy, Long updatedBy, boolean deleted, String hashTag) {
        super(id, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.hashTag = hashTag;
    }
}
