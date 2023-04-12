package com.instagram.instagram.domains;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Follow {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private User follower;
    @OneToOne
    private User following;
    @CreationTimestamp
    @Column(columnDefinition = "timestamp default now()")
    private LocalDateTime createdAt;

}

