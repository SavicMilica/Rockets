package products;

import common.BaseURL;
import io.restassured.RestAssured;
import io.restassured.response.Response;
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

        body.put("id", 1);
        body.put("title", "Rocket");
        body.put("price", 200);
        body.put("currency", "EUR");

        request.body(body);

        Response response = request.put("/products/1");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
