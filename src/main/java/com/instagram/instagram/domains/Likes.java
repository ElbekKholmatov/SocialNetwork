package com.instagram.instagram.domains;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Likes{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @OneToOne
   private User user;
   @CreationTimestamp
   @Column(columnDefinition = "timestamp default now()")
   private LocalDateTime createdAt;
}
