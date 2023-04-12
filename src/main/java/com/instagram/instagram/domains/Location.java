package com.instagram.instagram.domains;

import jakarta.persistence.Entity;
import lombok.*;
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location extends Auditable<Long> {
    private String address;
    private double latitude;
    private double longitude;
}
