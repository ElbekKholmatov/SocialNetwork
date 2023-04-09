package com.instagram.instagram.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString(callSuper = true)
public class Document extends Auditable<Long, Integer>{
    @Column(nullable = false)
    private String originalName;
    @Column(nullable = false)
    private String generatedName;
    @Column(nullable = false)
    private String extension;
    @Column(nullable = false)
    private String mimeType;
    @Column(nullable = false)
    private Long size;
    private String caption;
    private int likeCount;
    private int commentCount;
    private int showCount;

    @Builder(builderMethodName = "childBuilder")
    public Document(Long aLong, LocalDateTime createdAt, LocalDateTime updatedAt, Integer createdBy, Integer updatedBy, boolean deleted, String originalName, String generatedName, String extension, String mimeType, Long size, int likeCount, int commentCount, int showCount) {
        super(aLong, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.originalName = originalName;
        this.generatedName = generatedName;
        this.extension = extension;
        this.mimeType = mimeType;
        this.size = size;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.showCount = showCount;
    }

    @PrePersist
    public void persist(){
        this.generatedName = UUID.randomUUID() + "." + this.extension;
    }
}
