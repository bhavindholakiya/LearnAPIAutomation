package main.java.org.testcase;

import io.restassured.RestAssured;
import main.java.org.requestBody.Payload;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GooglePlace {
    public static void main(String[] args) {
        // Validate if Add Place API is working as expected

        //given - all input details
        //when - Submit the API, resource, http methods
        //Then - validate the response

        String Key = "key";
        String KeyValue = "qaclick123";
        String ContentType = "Content-Type";
        String ContentTypeValue = "application/json";
        String PlaceId = null;

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        given()
                .log().all()
                .queryParam(Key,KeyValue)
                .header(ContentType, ContentTypeValue)
                .body(Payload.AddPlace())
                .when()
                    .post("/maps/api/place/add/json")
                .then()
                    .log().all()
                    .assertThat().statusCode(200)
                        .body("scope", equalTo("APP"))
                        .header("Server", "Apache/2.4.41 (Ubuntu)");

        //Add Place -> Update place with New Address -> Get Place to validate if New Address is present.
    }
}
