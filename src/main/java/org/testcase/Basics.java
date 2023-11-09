package main.java.org.testcase;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basics {

    public static void main(String[] args) {

        int ExpectedQuantity = 3;
        // validating Add Place API is working as expected.

        /*
         * given: where we are setting and chaining up all the inputs like Headers, content-type, path params, request body (payload)
         * when: where we have to mention which HTTP request method to hit: GET, POST, PUT, PATCH, DELETE
         * then: where we are validation Response code, Response message, Response header, Response body
         * */

        RestAssured.baseURI= "https://dummyjson.com/";
        String response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(AddCart())
                .when().post("carts/add")
                .then()
                    .log().all()
                    .assertThat().statusCode(200).body("total", equalTo(621))
                    .header("server", "railway").extract().response().asString();

        JsonPath js = new JsonPath(response);
        int ActualQuantity = js.getInt("totalQuantity");

        Assert.assertEquals(ActualQuantity, ExpectedQuantity);
    }


    public static String AddCart(){
        return "{\n" +
                "    \"userId\": 1,\n" +
                "    \"products\": [\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"quantity\": 1\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 50,\n" +
                "            \"quantity\": 2\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }
}

