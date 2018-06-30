package main;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabean.googleapi.geocoding.GeocodingAPIResponse;
import javabean.main.PullServiceRequest;
import javabean.main.PullServiceResponse;
import javabean.main.TainanParkingRemainder;
import model.Park;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WebController {

    @RequestMapping(value = "/user/near/parkings", method = {RequestMethod.POST}, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public PullServiceResponse getUserNearParkList(@RequestBody String requestBody) {

        Gson gson = new GsonBuilder().disableHtmlEscaping().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();//創造Gson物件

        PullServiceRequest u = gson.fromJson(requestBody, PullServiceRequest.class);
        double goalLatitude = u.getQuery().getCoordinate().getLatitude();
        double goalLongitude = u.getQuery().getCoordinate().getLongitude();

        Park park = new Park();

        return park.getPullServiceResponseObject(goalLatitude, goalLongitude, readFile());
    }

    @RequestMapping(value = "/landmark/near/parkings", method = {RequestMethod.POST}, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public PullServiceResponse getPlaceNearParkList(@RequestBody String requestBody) {

        RestTemplate restTemplate = new RestTemplate();
        Gson gson = new GsonBuilder().disableHtmlEscaping().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();//創造Gson物件

        PullServiceRequest q = gson.fromJson(requestBody, PullServiceRequest.class);
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + q.getQuery().getLandmark() + "&key=AIzaSyAi8mU_NrWcOf87-o-PLixA4mExaEHInFE";

        String geocodingAPIResponseData = restTemplate.getForObject(url, String.class);
        GeocodingAPIResponse geocodingAPIResponse = gson.fromJson(geocodingAPIResponseData, GeocodingAPIResponse.class);
        double goalLatitude = geocodingAPIResponse.getResults().get(0).getGeometry().getLocation().getLat();
        double goalLongitude = geocodingAPIResponse.getResults().get(0).getGeometry().getLocation().getLng();

        Park park = new Park();

        return park.getPullServiceResponseObject(goalLatitude, goalLongitude, readFile());
    }

    @RequestMapping(value = "/remain/park/space", method = {RequestMethod.POST}, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public PullServiceResponse getRemainParkSpace(@RequestBody String requestBody) {

        Gson gson = new GsonBuilder().disableHtmlEscaping().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();//創造Gson物件

        PullServiceRequest q = gson.fromJson(requestBody, PullServiceRequest.class);
        TainanParkingRemainder[] tainanParkingRemainder = gson.fromJson(readFile(), TainanParkingRemainder[].class);

        PullServiceResponse p = null;
        for (TainanParkingRemainder d : tainanParkingRemainder) {//找出使用者問的停車場，並取出剩餘車位數量
            if (q.getQuery().getParkName().equals(d.getName())) {
                String message = q.getQuery().getParkName() + "還剩" + d.getCar() + "個車位";
                p = new PullServiceResponse(null, message, "ok");
                break;
            }
        }

        return p;
    }

    @RequestMapping(value = "/park/price", method = {RequestMethod.POST}, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public PullServiceResponse PullServiceResponse(@RequestBody String requestBody) {

        Gson gson = new GsonBuilder().disableHtmlEscaping().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();//創造Gson物件

        PullServiceRequest q = gson.fromJson(requestBody, PullServiceRequest.class);
        TainanParkingRemainder[] tainanParkingRemainder = gson.fromJson(readFile(), TainanParkingRemainder[].class);

        PullServiceResponse p = null;
        for (TainanParkingRemainder d : tainanParkingRemainder) {//找出使用者問的停車場的收費
            if (q.getQuery().getParkName().equals(d.getName())) {
                String message;
                if (d.getChargeFee() == null || d.getChargeFee().equals("") || d.getChargeFee().equals("公有收費停車場") || d.getChargeFee().contains("自行輸入")) {
                    message = "抱歉，此停車場目前未提供收費資訊。";
                } else if (d.getChargeFee().equals("公有免費停車場")) {
                    message = "您好，" + q.getQuery().getParkName() + "是免費的唷!";
                } else {
                    message = q.getQuery().getParkName() + "的收費方式為：" + d.getChargeFee();
                }
                p = new PullServiceResponse(null, message, "ok");
                break;
            }
        }
        
        return p;
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET})
    @ResponseBody
    public String hello(String requestBody) {
        return "Hello Xanxus";
    }

    public String readFile() {
        String content = null;
        String absolutePath = new File("").getAbsolutePath();
        try {
            content = new String(Files.readAllBytes(Paths.get(absolutePath + "/webapps/ParkingService/parkingData.json")));

        } catch (IOException ex) {
            Logger.getLogger(WebController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return content;
    }
}
