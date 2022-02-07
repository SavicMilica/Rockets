package products;

import common.BaseURL;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class UpdateProduct {

    @Test
    public void updateProduct() {
        BaseURL.setBaseUrl();

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");

        HashMap<String, Object> body = new HashMap();

        body.put("id", 2);
        body.put("title", "Rocket");
        body.put("price", 200);
        body.put("currency", "EUR");

        request.body(body);

        Response response = request.put("/products/2");

        ResponseBody myResponse = response.getBody();

        Assert.assertEquals(myResponse.path("title"), "Rocket", "title didn't match");
        Assert.assertEquals(myResponse.path("id"), Integer.valueOf(2), "id didn't match");
        Assert.assertEquals(myResponse.path("price"), Integer.valueOf(200), "price didn't match");
        Assert.assertEquals(myResponse.path("currency"), "EUR", "currency didn't match");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
