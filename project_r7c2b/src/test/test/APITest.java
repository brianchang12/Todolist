package test;

import network.API;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileReader;
import java.io.IOException;


public class APITest {
    API api;
    String str = "{\"request\":{\"type\":\"City\",\"query\":\"Richmond, Canada\",\"l" +
            "anguage\":\"en\",\"unit\":\"m\"},\"location\":{\"name\":\"Richmond\",\"count" +
            "ry\":\"Canada\",\"region\":\"British Columbia\",\"lat\":\"49.167\",\"lon\":\"-122.967\",\"timezone" +
            "_id\":\"America\\/Vancouver\",\"localtime\":\"2019-11-12 22:53\",\"localtime_epoch\":15735991" +
            "80,\"utc_offset\":\"-8.0\"},\"current\":{\"observation_time\":\"06:53 AM\",\"temperature\":9,\"" +
            "weather_code\":266,\"weather_icons\":[\"https:\\/\\/assets.weatherstack.com\\/images\\/wsymbols01_png_6" +
            "4\\/wsymbol_0033_cloudy_with_light_rain_night.png\"],\"weather_descriptions\":[\"Light Drizzle, " +
            "Mist\"],\"wind_speed\":9,\"wind_degree\":60,\"wind_dir\":\"ENE\",\"pressure\":1022,\"precip\":0.1" +
            ",\"humidity\":100,\"cloudcover\":100,\"feelslike\":8,\"uv_index\":0,\"visibility\":2,\"is_day\":\"no\"}}";


    @BeforeEach
    public void setup() {
        api = new API();

    }

    @Test
    public void testProduceStart() throws IOException, ParseException {
        api.setupJson(str);
        org.json.simple.parser.JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("./data/myJSON.json"));
        JSONObject jobj = (JSONObject) obj;
        String td = "Current Local Time And Date: " + jobj.get("localtime");
        String l = "Current Location: " + jobj.get("city");
        String temp = "Current Local Temperature: " + jobj.get("temp");
        assertEquals("Current Location: Richmond, Canada",l);
        assertEquals("Current Local Time And Date: " + jobj.get("localtime") ,td);
        assertEquals("Current Local Temperature: " + jobj.get("temp"),temp);

    }
}
