package model;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javabean.main.PullServiceResponse;
import javabean.main.TainanParkingRemainder;
import javabean.sub.Result;

public class Park {

    //goalLatitude與goalLongitude為目標經緯度。如：使用者目前所在經緯度或是地標經緯度
    //parkingData為台南市即時停車場開放資料，格式為json
    public PullServiceResponse getPullServiceResponseObject(double goalLatitude, double goalLongitude, String parkingData) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();//創造Gson物件
        TainanParkingRemainder[] tainanParkingRemainder = gson.fromJson(parkingData, TainanParkingRemainder[].class);

        List<TainanParkingRemainder> listOfParkingAreasInTheArea = getNeighboringParking(tainanParkingRemainder, goalLatitude, goalLongitude);
        Collections.sort(listOfParkingAreasInTheArea);//將距離由近到遠排序距離

        Result[] rArray = new Result[listOfParkingAreasInTheArea.size()];
        List<Result> responseResultObj = new ArrayList<>();
        String message = "幫您找到" + listOfParkingAreasInTheArea.size() + "個停車場：" + "\n";
        for (int i = 0; i < listOfParkingAreasInTheArea.size(); i++) {
            rArray[i] = new Result();
            rArray[i].setName(listOfParkingAreasInTheArea.get(i).getName().trim());
            rArray[i].setZone(listOfParkingAreasInTheArea.get(i).getZone().trim());
            rArray[i].setAddress(listOfParkingAreasInTheArea.get(i).getAddress().trim());
            rArray[i].setSurplusCar(listOfParkingAreasInTheArea.get(i).getCar());
            rArray[i].setDistance(listOfParkingAreasInTheArea.get(i).getDistance());
            rArray[i].setGoogleMapUrl(listOfParkingAreasInTheArea.get(i).getLnglat(), goalLatitude, goalLongitude);
            responseResultObj.add(rArray[i]);
            message += responseResultObj.get(i).getName() + "\n";
            message += "地址:" + responseResultObj.get(i).getAddress() + "\n";
            message += "剩餘車位:" + responseResultObj.get(i).getSurplusCar() + "\n";
            message += "距離:" + Calculate.distanceUnitFormat(responseResultObj.get(i).getDistance()) + "\n";
            message += "地圖路線:\n" + responseResultObj.get(i).getGoogleMapUrl() + "\n";
            message += "\n";
        }

        if (listOfParkingAreasInTheArea.size() == 0) {
            message = "抱歉，您附近沒有停車場哦";
        }

        return new PullServiceResponse(responseResultObj, message, "ok");
    }

    public List<TainanParkingRemainder> getNeighboringParking(TainanParkingRemainder[] tainanParkingRemainder, double goalLatitude, double goalLongitude) {//找出某目標1km範圍內的停車場

        List<Double> list = Calculate.getMaxMinLatitudeLongitude(goalLatitude, goalLongitude, 1);
        List<TainanParkingRemainder> listOfParkingAreasInTheArea = new ArrayList<>();//用來儲存在範圍內的停車場

        for (TainanParkingRemainder d : tainanParkingRemainder) {
            double parkLatitude = Double.parseDouble(d.getLnglat().substring(0, d.getLnglat().indexOf(",")));
            double parkLongitude = Double.parseDouble(d.getLnglat().substring(d.getLnglat().indexOf(",") + 1));
            double distance = Calculate.getTwoPointsDistance(goalLongitude, goalLatitude, parkLongitude, parkLatitude);//算出所有距離1km內的停車場和目標地點的距離，並封裝進去
            d.setDistance((int) distance);
            if (parkLongitude >= list.get(2) && parkLongitude <= list.get(3) && parkLatitude >= list.get(0) && parkLatitude <= list.get(1)) {
                listOfParkingAreasInTheArea.add(d);
            }
        }
        return listOfParkingAreasInTheArea;
    }
}
