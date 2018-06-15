package model;

import java.util.ArrayList;
import java.util.List;
import javabean.sub.Coordinate;

public class Calculate {

    //給兩個座標，並回傳兩座標的距離，以公尺為單位
    public static double getTwoPointsDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
        double radLatitude1 = latitude1 * Math.PI / 180;
        double radLatitude2 = latitude2 * Math.PI / 180;
        double l = radLatitude1 - radLatitude2;
        double p = longitude1 * Math.PI / 180 - longitude2 * Math.PI / 180;
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(l / 2), 2)
                + Math.cos(radLatitude1) * Math.cos(radLatitude2)
                * Math.pow(Math.sin(p / 2), 2)));
        distance = distance * 6378137.0;
        distance = Math.round(distance * 10000) / 10000;
        return distance;
    }

    //給一個座標和距離，並算出以此座標和此距離為基準的四個邊界座標點
    public static List<Coordinate> getBorder(double latitude, double longitude, double distance) {
        double latDiff = distance / 110.574;
        double lonDistance = 111.320 * Math.cos(latitude * Math.PI / 180);
        double lonDiff = distance / lonDistance;
        double N = latitude + Math.abs(latDiff);
        double S = latitude - Math.abs(latDiff);
        double E = longitude + Math.abs(lonDiff);
        double W = longitude - Math.abs(lonDiff);
        List<Coordinate> borderPoints = new ArrayList<>();
        Coordinate c1 = new Coordinate(latitude, E);
        Coordinate c2 = new Coordinate(S, longitude);
        Coordinate c3 = new Coordinate(latitude, W);
        Coordinate c4 = new Coordinate(N, longitude);
        borderPoints.add(c1);
        borderPoints.add(c2);
        borderPoints.add(c3);
        borderPoints.add(c4);
//        System.out.println(latitude + " " + E);
//        System.out.println(S + " " + longitude);
//        System.out.println(latitude + " " + W);
//        System.out.println(N + " " + longitude);
        return borderPoints;
    }
}
