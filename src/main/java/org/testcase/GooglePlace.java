package main.java.org.testcase;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
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
        String ExpectedAddress = "Tapi River Front, Near Cause way, Surat";

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String response = given()
                .log().all()
                .queryParam(Key,KeyValue)
                .header(ContentType, ContentTypeValue)
                .body(Payload.AddPlace())
                .when()
                    .post("/maps/api/place/add/json")
                .then()
                    //.log().all()
                    .assertThat().statusCode(200)
                        .body("scope", equalTo("APP"))
                        .header("Server", "Apache/2.4.41 (Ubuntu)")
                        .extract().response().asString();

        System.out.println("Response: "+response);

        JsonPath js = new JsonPath(response);
        PlaceId = js.getString("place_id");

        System.out.println("Extracted Place ID is: "+PlaceId);

        given().log().all()
                .queryParam(Key, KeyValue)
                .header(ContentType, ContentTypeValue)
                .body(Payload.UpdatePlace(PlaceId,ExpectedAddress))
                .when().put("/maps/api/place/update/json")
                .then()
                    .assertThat().log().all().statusCode(200)
                    .body("msg", equalTo("Address successfully updated"));

        String getPlaceResponse = given()
                .log().all()
                .queryParam(Key, KeyValue)
                .queryParam("place_id", PlaceId)
                .when().get("maps/api/place/get/json")
                .then().log().all()
                .assertThat().statusCode(200)
                    .header("Server", "Apache/2.4.41 (Ubuntu)")
                    .body("address", equalTo(ExpectedAddress))
                    .extract().response().asString();

        JsonPath js1 = new JsonPath(getPlaceResponse);
        String ActualAddress = js1.getString("address");

        System.out.println("Actual updated address is: " +ActualAddress);
    }
}
