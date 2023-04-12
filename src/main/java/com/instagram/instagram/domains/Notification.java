package com.instagram.instagram.domains;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Notification {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;


}
