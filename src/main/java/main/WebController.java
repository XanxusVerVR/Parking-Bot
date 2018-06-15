package main;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.atomic.AtomicLong;
import javabean.main.PullServiceRequestStructure;
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

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/fetch/{id}", method = GET)
    public Greeting greeting(@PathVariable("id") String id) {
        return new Greeting(counter.incrementAndGet(), String.format(template, id));
    }

    @RequestMapping(value = "/getUserNearParkList", method = {RequestMethod.POST}, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public String getUserNearParkList(@RequestBody String a) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();//創造Gson物件
        PullServiceRequestStructure newA = gson.fromJson(a, PullServiceRequestStructure.class);
        return "緯度是"+newA.getQuery().getCoordinate().getLatitude()+"經度是"+newA.getQuery().getCoordinate().getLongitude();
    }
}
