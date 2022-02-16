package tests.products;

import common.RestAssuredMethods;
import common.TestBase;
import constants.ApiEndpoints;
import constants.KeyParameters;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import apimethods.products.CreateProduct;

public class DeleteProductTest extends TestBase {
    Integer productId;

    @BeforeTest
    public void prepareData() {
        productId = CreateProduct.createProduct("Rocket", 0, "EUR").path(KeyParameters.ID);
    }

    @Test
    public void deleteProduct() {
        Response response = RestAssuredMethods.delete(ApiEndpoints.product(productId));
        Response myResponse = RestAssuredMethods.get(ApiEndpoints.product(productId));

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNull(myResponse.path(KeyParameters.ID), "id is not null");
        Assert.assertEquals(myResponse.getStatusCode(), 404);
    }
}
