package com.instagram.instagram.controller;

import com.instagram.instagram.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/location")

public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }


    @PostMapping(name = "/ip")
    public ResponseEntity<List<Map<String, Object>>> getClientIp(@RequestParam double latitude, @RequestParam double longitude) {
        List<Map<String, Object>> convert = locationService.convert(latitude, longitude);
        return ResponseEntity.ok(convert);
    }

    @GetMapping(name = "/getIp")
    public ResponseEntity<String> getFullInfo(@RequestParam double latitude, @RequestParam double longitude) {
        String address = locationService.convertToAddress(latitude, longitude);
        return ResponseEntity.ok(address);
    }



}
