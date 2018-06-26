package main;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabean.googleapi.geocoding.GeocodingAPIResponse;
import javabean.main.PullServiceRequest;
import javabean.main.PullServiceResponse;
import model.Park;
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
        Gson gson = new GsonBuilder().disableHtmlEscaping().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();//創造Gson物件

        String parkingData = restTemplate.getForObject("http://parkweb.tainan.gov.tw/api/parking.php", String.class);

        PullServiceRequest u = gson.fromJson(requestBody, PullServiceRequest.class);
        double goalLatitude = u.getQuery().getCoordinate().getLatitude();
        double goalLongitude = u.getQuery().getCoordinate().getLongitude();

        Park park = new Park();

        return park.getPullServiceResponseObject(goalLatitude, goalLongitude, parkingData);
    }

    @RequestMapping(value = "/getPlaceNearParkList", method = {RequestMethod.POST}, consumes = "application/json;charset=UTF-8")
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

        String parkingData = restTemplate.getForObject("http://parkweb.tainan.gov.tw/api/parking.php", String.class);

        Park park = new Park();

        return park.getPullServiceResponseObject(goalLatitude, goalLongitude, parkingData);
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
