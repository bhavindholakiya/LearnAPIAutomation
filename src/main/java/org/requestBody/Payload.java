package main.java.org.requestBody;

public class Payload {

    public static String AddPlace(){
        return "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"RiverFront house\",\n" +
                "  \"phone_number\": \"(+91) 985 569 6598\",\n" +
                "  \"address\": \"A-254, Front layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://techies2O.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}\n";
    }

    public static String UpdatePlace(String PlaceId, String ExpectedAddress){
        return "{\n" +
                "    \"place_id\": \""+PlaceId+"\",\n" +
                "    \"address\": \""+ExpectedAddress+"\",\n" +
                "    \"key\": \"qaclick123\"\n" +
                "}\n";
    }

    public static String CoursePrice(){
        return "{\n" +
                "  \"dashboard\": {\n" +
                "    \"purchaseAmount\": 910,\n" +
                "    \"website\": \"https://www.techies2pointO.com\"\n" +
                "  },\n" +
                "  \"courses\": [\n" +
                "    {\n" +
                "      \"title\": \"Selenium Python\",\n" +
                "      \"price\": 50,\n" +
                "      \"copies\": 6\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"Cypress\",\n" +
                "      \"price\": 40,\n" +
                "      \"copies\": 4\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"RPA\",\n" +
                "      \"price\": 45,\n" +
                "      \"copies\": 10\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String AddBook(String isbn, String aisle) {
        String payload = "{\n" +
                "    \"name\": \"Learn API Automation with Java\",\n" +
                "    \"isbn\": \""+isbn+"\",\n" +
                "    \"aisle\": \""+aisle+"\",\n" +
                "    \"author\": \"Bhavin D.\"\n" +
                "}";
        return payload;
    }

    public static String DeleteBook(String ID){
        String payload = "{\n" +
                "    \"ID\": \""+ID+"\"\n" +
                "}";
        return payload;
    }
}
