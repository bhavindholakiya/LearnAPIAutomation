package main.java.org.testcase;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import main.java.org.requestBody.Helper;
import main.java.org.requestBody.Payload;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {

    String BookID=null;

    @Test
    public void AddBook(){
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(Payload.AddBook("MBD","22012"))
                .when().post("/Library/Addbook.php")
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = Helper.RawToJson(response);
        BookID = js.getString("ID");
        System.out.println("The Book ID is: "+BookID);

        // Deleting above created book.
        String DeleteRes = given()
                .header("Content-Type", "application/json")
                .body(Payload.DeleteBook(BookID))
                .when().post("/Library/DeleteBook.php")
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath djs = Helper.RawToJson(DeleteRes);
        String msg = djs.get("msg");

        Assert.assertEquals(msg, "book is successfully deleted");

    }
}
