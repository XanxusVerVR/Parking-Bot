package main;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabean.main.PullServiceRequest;
import javabean.main.PullServiceResponse;
import javabean.main.TainanParkingRemainder;
import javabean.sub.Result;
import model.Calculate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WebController {

    @RequestMapping(value = "/getUserNearParkList", method = {RequestMethod.POST}, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public PullServiceResponse getUserNearParkList(@RequestBody String requestBody) {

        RestTemplate restTemplate = new RestTemplate();
        String parkingData = restTemplate.getForObject("http://parkweb.tainan.gov.tw/api/parking.php", String.class);
        Gson gson = new GsonBuilder().disableHtmlEscaping().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();//創造Gson物件
        TainanParkingRemainder[] tainanParkingRemainder = gson.fromJson(parkingData, TainanParkingRemainder[].class);

        PullServiceRequest u = gson.fromJson(requestBody, PullServiceRequest.class);
        double goalLatitude = u.getQuery().getCoordinate().getLatitude();
        double goalLongitude = u.getQuery().getCoordinate().getLongitude();

        List<Double> list = Calculate.getMaxMinLatitudeLongitude(goalLatitude, goalLongitude, 1);
        List<TainanParkingRemainder> listOfParkingAreasInTheArea = new ArrayList<>();//用來儲存在範圍內的停車場

        for (TainanParkingRemainder d : tainanParkingRemainder) {//找出1km範圍內的停車場
            d.setLnglat(d.getLnglat().replaceAll(" ", ""));
            double parkLatitude = Double.parseDouble(d.getLnglat().substring(0, d.getLnglat().indexOf(",")));
            double parkLongitude = Double.parseDouble(d.getLnglat().substring(d.getLnglat().indexOf(",") + 1));
            if (parkLongitude >= list.get(2) && parkLongitude <= list.get(3) && parkLatitude >= list.get(0) && parkLatitude <= list.get(1)) {
                listOfParkingAreasInTheArea.add(d);
            }
        }

        for (TainanParkingRemainder l : listOfParkingAreasInTheArea) {//算出所有距離1km內的停車場和目標地點的距離，並封裝進去
            double parkLatitude = Double.parseDouble(l.getLnglat().substring(0, l.getLnglat().indexOf(",")));
            double parkLongitude = Double.parseDouble(l.getLnglat().substring(l.getLnglat().indexOf(",") + 1));
            double distance = Calculate.getTwoPointsDistance(goalLongitude, goalLatitude, parkLongitude, parkLatitude);
            l.setDistance((int) distance);
        }

        Collections.sort(listOfParkingAreasInTheArea);//由近到遠排序距離

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
            message += "距離您:" + responseResultObj.get(i).getDistance() + "公尺" + "\n";
            message += "地圖路線:\n" + responseResultObj.get(i).getGoogleMapUrl() + "\n";
            message += "\n";
        }

        if (listOfParkingAreasInTheArea.size() == 0) {
            message = "抱歉，您附近沒有停車場哦";
        }

        PullServiceResponse p = new PullServiceResponse(responseResultObj, message, "ok");

        return p;
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET})
    @ResponseBody
    public String hello(String requestBody) {
        return "Hello Xanxus";
    }

    public void writeFile(String data) {
        try {
            Files.write(Paths.get("parkingData.json"), data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFile() {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get("parkingData.json")));

        } catch (IOException ex) {
            Logger.getLogger(WebController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return content;
    }
}
