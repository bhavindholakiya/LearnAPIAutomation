package main.java.org.testcase;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basics {

    JsonPath js;

    public static void main(String[] args) {

        // validating Add Cart API is working as expected.

        /*
         * given: where we are setting and chaining up all the inputs like Headers, content-type, path params, request body (payload)
         * when: where we have to mention which HTTP request method to hit: GET, POST, PUT, PATCH, DELETE
         * then: where we are validation Response code, Response message, Response header, Response body
         * */

        int expectedQuantity = 3;
        String expectedProductTitle = "Brown Perfume";
        int productID = 0;

        RestAssured.baseURI= "https://dummyjson.com/";
        Basics obj = new Basics();

        // Get all the products
        JsonPath js = new JsonPath(obj.GetAllProducts());
        int NoOfProduct = js.get("products.size()");

        String titleFirstProduct = js.get("products[0].title");
        System.out.println("First Product is: " +titleFirstProduct);

        // Get ID of an item and then pass to ID to next request for cart
        for(int i=0; i<NoOfProduct; i++){
            String productTitle = js.getString("products["+i+"].title");
            if(productTitle.equalsIgnoreCase(expectedProductTitle)){
                productID = js.getInt("products["+i+"].id");
                break;
            }
        }

        // Add products in Cart by ID and quantity
        obj.AddProductToCart(productID);

    }

    public String GetAllProducts(){
        String productList = given()
                    .header("Content-Type", "application/json")
                .when()
                    .get("/products")
                .then()
                    .log().all()
                    .assertThat().statusCode(200).extract().response().asString();

        // Print all the items name
        JsonPath ProList = new JsonPath(productList);
        int ProductsCount = ProList.getInt("products.size()");
        System.out.println("No. of products: " +ProductsCount);

        return productList;
    }

    public void AddProductToCart(int productID){
        String response = given()
                    .log().all()
                    .header("Content-Type", "application/json")
                    .body(AddCart(productID))
                .when()
                    .post("carts/add")
                .then()
                    .log().all()
                    .assertThat().statusCode(200)
                    //.body("total", equalTo(621))
                    .header("server", "railway").extract().response().asString();
    }


    public static String AddCart(int productID){
        return "{\n" +
                "    \"userId\": 1,\n" +
                "    \"products\": [\n" +
                "        {\n" +
                "            \"id\": \""+productID+"\",\n" +
                "            \"quantity\": 1\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }
}

