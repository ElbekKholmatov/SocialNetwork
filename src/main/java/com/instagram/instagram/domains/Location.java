package com.instagram.instagram.domains;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location extends Auditable<Long> {

    private String address;

    private double latitude;

    private double longitude;

    @Builder(builderMethodName = "childBuilder")
    public Location(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Long createdBy, Long updatedBy, boolean deleted, String address, double latitude, double longitude) {
        super(id, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
