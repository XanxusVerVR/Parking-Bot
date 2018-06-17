package model;
/*
參考資料：
算出範圍內最大最小經緯度與算出兩點距離：
https://my.oschina.net/freegeek/blog/221341
算出範圍內最大最小經緯度：
https://blog.csdn.net/yusewuhen/article/details/38402721
給經緯度和距離，以此為基準算出四方形四個點的經緯度
http://wp.mlab.tw/?p=2200
*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        borderPoints.add(new Coordinate(latitude, E));
        borderPoints.add(new Coordinate(S, longitude));
        borderPoints.add(new Coordinate(latitude, W));
        borderPoints.add(new Coordinate(N, longitude));
        return borderPoints;
    }

    //取得最小最大經緯度四個值
    public static List<Double> getMaxMinLatitudeLongitude(double latitude, double longitude, double distance) {
        double r = 6371; //地球半徑千米
        double dlng = 2 * Math.asin(Math.sin(distance / (2 * r)) / Math.cos(latitude * Math.PI / 180));
        dlng = dlng * 180 / Math.PI; //角度轉為弧度
        double dlat = distance / r;
        dlat = dlat * 180 / Math.PI;
        double minlat = latitude - dlat;
        double maxlat = latitude + dlat;
        double minlng = longitude - dlng;
        double maxlng = longitude + dlng;
        List<Double> list = new ArrayList<>();
        list.add(minlat);
        list.add(maxlat);
        list.add(minlng);
        list.add(maxlng);
        return list;
    }
}
