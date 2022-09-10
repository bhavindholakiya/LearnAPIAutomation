package main.java.org.testcase;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import main.java.org.requestBody.Helper;
import main.java.org.requestBody.Payload;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {

    String BookID=null;

    @Test(dataProvider = "Books")
    public void AddBook(String isbn, String aisle){
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given()
                //.log().all()
                .header("Content-Type", "application/json")
                .body(Payload.AddBook(isbn,aisle))
                .when().post("/Library/Addbook.php")
                .then()
                //.log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = Helper.RawToJson(response);
        BookID = js.getString("ID");
        System.out.println("The Book ID is: "+BookID);
    }

    @Test(dataProvider = "Books")
    public void DeleteBookByID(String isbn, String aisle){
        // Deleting above created book.

        String BookID = isbn.concat(aisle);

        String DeleteRes = given()
                .header("Content-Type", "application/json")
                .body(Payload.DeleteBook(BookID))
                .when().post("/Library/DeleteBook.php")
                .then()
                //.log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath djs = Helper.RawToJson(DeleteRes);
        String msg = djs.get("msg");

        Assert.assertEquals(msg, "book is successfully deleted");
    }

    @DataProvider(name = "Books")
    public Object[][] getData(){
        return new Object[][] {
                {"Test1","2001"},
                {"Test2","2002"},
                {"Test3","2003"},
        };
    }
}
