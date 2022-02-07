package products;
import common.BaseURL;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;



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

        ResponseBody myResponse = response.getBody();
        //System.out.println("Response Body is: " + myResponse.asString());

        Assert.assertEquals(myResponse.path("title"), "Falcon 15", "title didn't match");
        Assert.assertNotNull(myResponse.path("id"), "id is null");
        Assert.assertEquals(myResponse.path("price"), Integer.valueOf(50), "price didn't match");
        Assert.assertEquals(myResponse.path("currency"), "EUR", "currency didn't match");

        int code = response.getStatusCode();

        Assert.assertEquals(code, 201);


    }

}


