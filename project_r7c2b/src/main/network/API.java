package network;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;


public class API {
    // taken from Course, Youtube
    //https://www.youtube.com/watch?v=wDVH3qnXv74
    //https://www.youtube.com/watch?v=cFCgFlqF5kw&t=3
    //http://zetcode.com/articles/javareadwebpage/

    //EFFECTS: Extracts information from an API and converts it into a String

    public static void obtainInfo() throws MalformedURLException, IOException {
        String apikey = "204b48d4f90ff408e1688e8f813ab0b6";

        BufferedReader br = null;


        try {
            String theURL = "http://api.weatherstack.com/current?access_key=" + apikey + "&query=Richmond,Canada";
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }
            setupJson(sb.toString());


        } finally {

            if (br != null) {
                br.close();
            }
        }
    }


    //REQUIRES: A string (sb) longer than or equals to 362 characters
    //EFFECTS: Extract the string information on time, temperature, and location.
    //writes the strings to the file
    public static void setupJson(String sb) throws IOException {
        String lt = sb.substring(238, 254);
        String ct = sb.substring(35, 51);
        String tp = sb.substring(361, 362) + " degrees Celsius";
        JSONObject obj = new JSONObject();
        obj.put("localtime", lt);
        obj.put("city", ct);
        obj.put("temp", tp);
        FileWriter file = new FileWriter("./data/myJSON.json");
        file.write(obj.toString());
        file.flush();
    }

    //EFFECTS: Parses through file, extract the strings and returnts it and
    // prints to the console
    public static String produceStart() throws IOException, ParseException {
        org.json.simple.parser.JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("./data/myJSON.json"));
        JSONObject jobj = (JSONObject) obj;
        String td = "Current Local Time And Date: " + jobj.get("localtime");
        String l = "Current Location: " + jobj.get("city");
        String temp = "Current Local Temperature: " + jobj.get("temp");
        System.out.println(l);
        System.out.println(td);
        System.out.println(temp);
        System.out.println(" ");
        return td + "\n" + l + "\n" + temp;


    }


}