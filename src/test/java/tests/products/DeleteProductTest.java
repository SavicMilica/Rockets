package tests.products;

import common.TestBase;
import constants.KeyParameters;
import data.providers.ProductData;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import apimethods.ProductAPI;

public class DeleteProductTest extends TestBase {
    Integer productId;

    @BeforeTest
    public void prepareData() {
        productId = ProductAPI.createProduct(ProductData.prepareProductRequest()).path(KeyParameters.ID);
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
