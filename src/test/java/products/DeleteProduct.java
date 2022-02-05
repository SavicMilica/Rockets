package products;

import common.BaseURL;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteProduct {

    @Test
    public void deleteProduct() {

        BaseURL.setBaseUrl();

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");

        Response response = request.delete("/products/1");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 204);
    }
}
