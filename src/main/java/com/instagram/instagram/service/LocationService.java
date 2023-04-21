package com.instagram.instagram.service;

import com.instagram.instagram.config.security.SessionUser;
import com.instagram.instagram.domains.Location;
import com.instagram.instagram.repository.LocationRepository;
import com.instagram.instagram.utils.AddressFinder;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


import javax.json.Json;
import javax.json.JsonObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

@Service
public class LocationService {

    private final DatabaseReader databaseReader;
    private final SessionUser sessionUser;
    private final LocationRepository locationRepository;

    public LocationService(SessionUser sessionUser, LocationRepository locationRepository) throws IOException {
        this.sessionUser = sessionUser;
        File databaseFile = new File("src/main/resources/static/forLocation/GeoLite2-City.mmdb");
        this.databaseReader = new DatabaseReader.Builder(databaseFile).build();
        this.locationRepository = locationRepository;
    }

    public void ipToLatLong(String ip) throws Exception {
        String apiUrl = "http://ip-api.com/json/" + ip;

        URL url = new URL(apiUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JsonObject jsonResult = Json.createReader(new StringReader(response.toString())).readObject();
        double latitude = jsonResult.getJsonNumber("lat").doubleValue();
        double longitude = jsonResult.getJsonNumber("lon").doubleValue();
        System.out.println("Latitude: " + latitude);
        System.out.println("Longitude: " + longitude);
        System.out.println(convert(latitude, longitude));
    }


    public String convert(double latitude, double longitude) {

        String urlString = "https://nominatim.openstreetmap.org/reverse?lat="
                + latitude + "&lon=" + longitude + "&format=jsonv2";
        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                result.append(inputLine);
            }

            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject json = new JSONObject(result.toString());
            JSONObject address = json.getJSONObject("address");
//            String city = address.getString("city");
//            String country = address.getString("country");
//            String county = address.getString("county");

            Location location = locationRepository.save(
                    Location.childBuilder()
                            .address(String.valueOf(address))
                            .longitude(longitude)
                            .latitude(latitude)
                            .createdBy(sessionUser.id())
                            .build());
            return String.valueOf(location.getAddress());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    public Location getGeoLocation(String ipAddress) throws Exception {
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        CityResponse cityResponse = databaseReader.city(inetAddress);
        double latitude = cityResponse.getLocation().getLatitude();
        double longitude = cityResponse.getLocation().getLongitude();
        AddressFinder addressFinder = new AddressFinder("AIzaSyBqOwNhBE9JzmkRTexqVmNzqATzx-w7ehs");
        String address = addressFinder.getAddress(latitude, longitude);
        return new Location(address, latitude, longitude);
    }

    public Location findById(Long location) {
        return locationRepository.findById(location).orElseThrow(() -> new RuntimeException("location not found"));
    }
}
