package com.instagram.instagram.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LocationDto implements Serializable {
    private final String address;
    private final double latitude;
    private final double longitude;
}