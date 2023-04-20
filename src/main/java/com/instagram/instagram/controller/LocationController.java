package com.instagram.instagram.controller;

import com.instagram.instagram.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/location")

public class LocationController {

    private final LocationService locationService;
    private Long userId;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }


    @PostMapping(name = "/ip")
    public ResponseEntity<String> getClientIp(@RequestParam double latitude, @RequestParam double longitude) {
        String address = locationService.convert(latitude, longitude);
        return ResponseEntity.ok(address);
    }

    @GetMapping("/ip")
    public String getClientIp2(double latitude, double longitude) throws Exception {


//        String remoteAddr = "";
//
//        if (request != null) {
//            remoteAddr = request.getHeader("X-FORWARDED-FOR");
//            if (remoteAddr == null || "".equals(remoteAddr)) {
//                remoteAddr = request.getRemoteAddr();
//            }
//        }
//        LocationService locationService = new LocationService();
//        Location location = locationService.getGeoLocation(remoteAddr);
//
//        LocationService.ipToLatLong(remoteAddr);

        return locationService.convert(latitude, longitude);


    }
}
