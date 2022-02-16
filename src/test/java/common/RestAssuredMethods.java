package common;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestAssuredMethods {

    public static Response delete(String apiEndpoint) {
        return given().contentType(ContentType.JSON).delete(apiEndpoint);
    }

    public static Response get(String apiEndpoint) {
        return given().contentType(ContentType.JSON).get(apiEndpoint);
    }

    public static Response post(Object body, String apiEndpoint) {
        return given().contentType(ContentType.JSON).body(body).post(apiEndpoint);
    }

    public static Response put(Object body, String apiEndpoint) {
        return given().contentType(ContentType.JSON).body(body).put(apiEndpoint);
    }
}
