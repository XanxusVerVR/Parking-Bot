package main;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabean.main.PullServiceRequest;
import javabean.main.TainanParkingRemainder;
import model.Calculate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/parkingbot")
public class WebController {

    @RequestMapping(value = "/getUserNearParkList", method = {RequestMethod.POST}, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public void getUserNearParkList(@RequestBody String requestBody) {
        RestTemplate restTemplate = new RestTemplate();
        String parkingData = restTemplate.getForObject("http://parkweb.tainan.gov.tw/api/parking.php", String.class);
        Gson gson = new GsonBuilder().disableHtmlEscaping().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();//創造Gson物件
        TainanParkingRemainder[] tainanParkingRemainder = gson.fromJson(parkingData, TainanParkingRemainder[].class);
        PullServiceRequest u = gson.fromJson(requestBody, PullServiceRequest.class);
        List<Double> list = Calculate.getMaxMinLatitudeLongitude(u.getQuery().getCoordinate().getLatitude(), u.getQuery().getCoordinate().getLongitude(), 1);
        List<TainanParkingRemainder> listOfParkingAreasInTheArea = new ArrayList<>();//用來儲存在範圍內的停車場
        for (TainanParkingRemainder d : tainanParkingRemainder) {
            double parkLatitude = Double.parseDouble(d.getLnglat().substring(0, d.getLnglat().indexOf(",")));
            double parkLongitude = Double.parseDouble(d.getLnglat().substring(d.getLnglat().indexOf(",") + 1));
            if (parkLongitude >= list.get(2) && parkLongitude <= list.get(3) && parkLatitude >= list.get(0) && parkLatitude <= list.get(1)) {
                listOfParkingAreasInTheArea.add(d);
            }
        }
        writeFile(parkingData);
        System.out.println(readFile());
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
