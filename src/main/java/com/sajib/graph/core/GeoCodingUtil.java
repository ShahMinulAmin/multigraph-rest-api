package com.sajib.graph.core;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.sajib.graph.types.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by sajib on 2/20/19.
 */
public class GeoCodingUtil {

    private static final Logger LOG = Logger.getLogger(GeoCodingUtil.class.getName());

    public static List<String> getDerivedDestinations(Map<String, Coordinate> coordinateMap, String destionation) {
        // get coordiate of destionation by reverse geocoding
        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyC8-HBw08K4PVZpXE5PtGIPKrjU_t1Nuhg");
        GeocodingResult[] results = new GeocodingResult[0];
        try {
            results = GeocodingApi.geocode(context, destionation).await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOG.info("Target city: " + results[0].formattedAddress);
        Coordinate targetCoordinate = new Coordinate(results[0].geometry.location.lat, results[0].geometry.location.lng);
        LOG.info("Target coordinate: " + targetCoordinate.getLatitude() + ", " + targetCoordinate.getLongitude());

        List<String> derivedDestinationList = new ArrayList<String>();
        for (String city : coordinateMap.keySet()) {
            Coordinate cityCoordinate = coordinateMap.get(city);
            // calculate distances from all existing cities
            Double distanceInKm = DistanceCalculator.distanceInKM(cityCoordinate.getLatitude(), cityCoordinate.getLongitude(),
                    targetCoordinate.getLatitude(), targetCoordinate.getLongitude());
            // if distance is less or equal to 50km, add the existing city to derived list
            if (distanceInKm <= 50) {
                LOG.info("Nearest City: " + city + "; Distance(Km): " + distanceInKm);
                derivedDestinationList.add(city);
            }
        }

        return derivedDestinationList;
    }
}

