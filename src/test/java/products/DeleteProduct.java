package products;

import common.BaseURL;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteProduct {

    @Test
    public void deleteProduct() {

        BaseURL.setBaseUrl();

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");

        Response response = request.delete("/products/4");

        ResponseBody myResponse = response.getBody();

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
