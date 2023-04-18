package com.instagram.instagram.domains;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Link extends Auditable<Long>{
    private String name;
    private String link;

    @Builder(builderMethodName = "childBuilder")
    public Link(Long integer, LocalDateTime createdAt, LocalDateTime updatedAt, Long createdBy, Long updatedBy, boolean deleted, String name, String link) {
        super(integer, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.name = name;
        this.link = link;
    }
}
