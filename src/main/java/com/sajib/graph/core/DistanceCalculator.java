package com.sajib.graph.core;

/**
 * Calculate distance between two geo coordinates using Haversine Algorithm.
 *
 * Created by sajib on 2/20/19.
 */
public class DistanceCalculator {
    static final double EQUATORIAL_EARTH_RADIUS = 6378.1370D;
    static final double DEGREE_TO_RADIAN = (Math.PI / 180D);

    public static int distanceInM(double lat1, double long1, double lat2, double long2) {
        return (int) (1000D * distanceInKM(lat1, long1, lat2, long2));
    }

    public static double distanceInKM(double lat1, double long1, double lat2, double long2) {
        double dlong = (long2 - long1) * DEGREE_TO_RADIAN;
        double dlat = (lat2 - lat1) * DEGREE_TO_RADIAN;
        double a = Math.pow(Math.sin(dlat / 2D), 2D)
                + Math.cos(lat1 * DEGREE_TO_RADIAN) * Math.cos(lat2 * DEGREE_TO_RADIAN)
                * Math.pow(Math.sin(dlong / 2D), 2D);
        double c = 2D * Math.atan2(Math.sqrt(a), Math.sqrt(1D - a));
        double d = EQUATORIAL_EARTH_RADIUS * c;
        return d;
    }
}
