package main.java.org.requestBody;

import io.restassured.path.json.JsonPath;

public class Helper {

    public static JsonPath RawToJson(String response){
        JsonPath js = new JsonPath(response);
        return js;
    }
}
