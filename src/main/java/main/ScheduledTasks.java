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
import javabean.main.TainanParkingRemainder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ScheduledTasks {

    @Scheduled(fixedRate = 300 * 1000)
    public void writeParkingData() throws IOException {//寫入即時的停車場開放資料，並格式化整理
        RestTemplate restTemplate = new RestTemplate();
        String parkingData = restTemplate.getForObject("http://parkweb.tainan.gov.tw/api/parking.php", String.class);
        Gson gson = new GsonBuilder().disableHtmlEscaping().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();//創造Gson物件
        TainanParkingRemainder[] tainanParkingRemainder = gson.fromJson(parkingData, TainanParkingRemainder[].class);
        for (TainanParkingRemainder d : tainanParkingRemainder) {
            d.setLnglat(d.getLnglat().replaceAll(" ", ""));
            String nameFormat = d.getName().trim().replaceAll("\\(", "").replaceAll("\\)", "");
            d.setName(nameFormat);
        }
        TainanParkingRemainder.TAINANPARKINGREMAINDER = tainanParkingRemainder;
        writeFile(gson.toJson(tainanParkingRemainder));
    }

    public void writeFile(String data) {
        String absolutePath = new File("").getAbsolutePath();
        try {
            Files.write(Paths.get(absolutePath + "/webapps/ParkingService/parkingData.json"), data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
