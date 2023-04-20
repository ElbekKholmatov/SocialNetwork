package com.instagram.instagram.utils;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

public class AddressFinder {

    private final GeoApiContext context;


    public AddressFinder(String apiKey) {
        this.context = new GeoApiContext.Builder().apiKey(apiKey).build();
    }

    public String  getAddress(double latitude, double longitude) throws Exception {
        GeocodingResult[] results = GeocodingApi.reverseGeocode(context, new LatLng(latitude, longitude)).await();
        if (results != null && results.length > 0) {
            return results[0].formattedAddress;
        } else {
            return null;
        }
    }

}