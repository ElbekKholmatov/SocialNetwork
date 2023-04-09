package com.instagram.instagram.domains;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString(callSuper = true)
public class Link extends Auditable<Integer, Integer>{
    private String name;
    private String link;

    @Builder(builderMethodName = "childBuilder")
    public Link(Integer integer, LocalDateTime createdAt, LocalDateTime updatedAt, Integer createdBy, Integer updatedBy, boolean deleted, String name, String link) {
        super(integer, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.name = name;
        this.link = link;
    }
}
