package products;

import common.BaseURL;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetProduct {

    @Test
    public void getProduct() {
        BaseURL.setBaseUrl();

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");

        Response response = request.get("/products/1");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
