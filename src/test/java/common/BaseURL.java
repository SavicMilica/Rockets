package common;

import io.restassured.RestAssured;

public class BaseURL {

    public static void setBaseUrl() {
     RestAssured.baseURI = "http://localhost:3004";
    }

}




