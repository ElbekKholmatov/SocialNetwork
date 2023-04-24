package com.instagram.instagram.domains;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

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
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "timestamp default now()")
    private LocalDateTime updatedAt;
    @CreatedBy
    private ID createdBy;
    @LastModifiedBy
    private ID updatedBy;

    @Column(columnDefinition = "boolean default 'f'")
    private boolean deleted;
}
