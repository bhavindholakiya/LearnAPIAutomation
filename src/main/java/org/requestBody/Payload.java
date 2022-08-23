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
}
