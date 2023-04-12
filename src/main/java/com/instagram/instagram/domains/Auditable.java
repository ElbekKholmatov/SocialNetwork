package com.instagram.instagram.domains;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@MappedSuperclass
public class Auditable<ID extends Serializable> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "timestamp default now()")
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ID createdBy;
    private ID updatedBy;

    @Column(columnDefinition = "boolean default 'f'")
    private boolean deleted;
}
