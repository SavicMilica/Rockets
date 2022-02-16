package tests.products;


import common.TestBase;
import constants.KeyParameters;
import io.restassured.response.Response;
import models.products.ProductRequest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import apimethods.ProductAPI;

public class DeleteProductTest extends TestBase {
    Integer productId;

    @BeforeTest
    public void prepareData() {
        productId = ProductAPI.createProduct(new ProductRequest("Rocket", 0, "EUR")).path(KeyParameters.ID);
    }

    @Test
    public void deleteProduct() {
        Response response = ProductAPI.deleteProduct(productId);
        Response myResponse = ProductAPI.getProductById(productId);

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
        Assert.assertNull(myResponse.path(KeyParameters.ID), "id is not null");
        Assert.assertEquals(myResponse.getStatusCode(), 404);
    }
}
