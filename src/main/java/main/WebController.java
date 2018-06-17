package main;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import javabean.main.PullServiceRequest;
import javabean.sub.Coordinate;
import model.Calculate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parkingbot")
public class WebController {

    @RequestMapping(value = "/getUserNearParkList", method = {RequestMethod.POST}, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public String getUserNearParkList(@RequestBody String a) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();//創造Gson物件
        PullServiceRequest newA = gson.fromJson(a, PullServiceRequest.class);
        List<Double> list = Calculate.getMaxMinLatitudeLongitude(newA.getQuery().getCoordinate().getLatitude(),newA.getQuery().getCoordinate().getLongitude(),1);
        double myLatitude = 22.995307;
        double myLongitude = 120.214746;

        if(myLongitude>=list.get(2)&&myLongitude<=list.get(3)&&myLatitude>=list.get(0)&&myLatitude<=list.get(1)){
            return "此座標在裡面";
        }
        else{
            return "此座標不在裡面";
        }
    }
}
