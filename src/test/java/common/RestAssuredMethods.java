package common;

import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestAssuredMethods {

    public static Response delete(String apiEndpoint) {
        return given().contentType(ContentType.JSON).when().delete(apiEndpoint).then().extract().response();
    }

    public static Response get(String apiEndpoint) {
        return given().contentType(ContentType.JSON).when().get(apiEndpoint).then().extract().response();
    }

    public static Response post(Object body, String apiEndpoint) {
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(body);
        return given().contentType(ContentType.JSON).body(json).when().post(apiEndpoint).then().extract().response();
    }

    public static Response put(Object body, String apiEndpoint) {
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(body);
        return given().contentType(ContentType.JSON).body(json).when().put(apiEndpoint).then().extract().response();
    }
}
