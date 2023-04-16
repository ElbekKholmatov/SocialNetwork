package com.instagram.instagram.service;

import com.instagram.instagram.domains.Location;
import com.instagram.instagram.utils.AddressFinder;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
@Service
public class LocationService {

    private final DatabaseReader databaseReader;

    public LocationService() throws IOException {
        File databaseFile = new File("src/main/resources/static/forLocation/GeoLite2-City.mmdb");
        this.databaseReader = new DatabaseReader.Builder(databaseFile).build();
    }


    public Location getGeoLocation(String ipAddress) throws Exception {
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        CityResponse cityResponse = databaseReader.city(inetAddress);
        double latitude = cityResponse.getLocation().getLatitude();
        double longitude = cityResponse.getLocation().getLongitude();
        AddressFinder addressFinder=new AddressFinder("AIzaSyBqOwNhBE9JzmkRTexqVmNzqATzx-w7ehs");
        String address = addressFinder.getAddress(latitude, longitude);
        return new Location(address,latitude,longitude);
    }

}
