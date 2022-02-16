package tests.products;
import common.RestAssuredMethods;
import common.TestBase;
import constants.ApiEndpoints;
import constants.KeyParameters;
import io.restassured.response.Response;
import models.products.ProductRequest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import apimethods.ProductAPI;
import static constants.KeyParameters.ID;

public class GetProductTest extends TestBase {

    private Integer productId;

    @BeforeClass
    public void prepareData() {
        productId = ProductAPI.createProduct(new ProductRequest("Rocket", 15, "EUR")).path(KeyParameters.ID);
    }

    @Test
    public void getProduct() {
        Response response = ProductAPI.getProductById(productId);
        Assert.assertEquals(response.path(ID), productId, "id didn't match");
        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
