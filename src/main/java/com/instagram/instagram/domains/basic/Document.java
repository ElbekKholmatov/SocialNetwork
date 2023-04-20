package com.instagram.instagram.domains.basic;

import com.instagram.instagram.domains.Auditable;
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
@AllArgsConstructor
@ToString(callSuper = true)
public class Document extends Auditable<Long> {
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
    private String path;

    @Builder(builderMethodName = "childBuilder")
    public Document(Long aLong, LocalDateTime createdAt, LocalDateTime updatedAt, Long createdBy, Long updatedBy, boolean deleted, String originalName, String generatedName, String extension, String mimeType, Long size, String path) {
        super(aLong, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.originalName = originalName;
        this.generatedName = generatedName;
        this.extension = extension;
        this.mimeType = mimeType;
        this.size = size;
        this.path = path;
    }

    @PrePersist
    public void persist(){
        this.generatedName = UUID.randomUUID() + "." + this.extension;
    }
}
