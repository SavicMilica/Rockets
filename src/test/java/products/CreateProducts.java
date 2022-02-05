package products;
import common.BaseURL;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class CreateProducts {

    @Test
    public void createProduct() {
        BaseURL.setBaseUrl();

        RequestSpecification request = given();

        request.header("Content-Type", "application/json");

        HashMap<String, Object> body = new HashMap<>();
        body.put("title", "Falcon 15");
        body.put("price", 50);
        body.put("currency", "EUR");

        request.body(body);

        Response response = request.post("/products");

        int code = response.getStatusCode();

        Assert.assertEquals(code, 201);
    }

}


