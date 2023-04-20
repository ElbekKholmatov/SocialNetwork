package com.instagram.instagram.controller;

import com.instagram.instagram.domains.Location;
import com.instagram.instagram.dto.LocationDto;
import com.instagram.instagram.repository.LocationRepository;
import com.instagram.instagram.service.LocationService;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

@RestController
@RequestMapping("/api/v1/location")

public class LocationController {

    private Long userId;

    @GetMapping("/ip")
    public String getClientIp(double latitude, double longitude) throws Exception {


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

        return LocationService.convert(latitude,longitude);




    }
}
