package tests.products;
import apimethods.ProductAPI;
import common.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import models.products.ProductRequest;
import static constants.KeyParameters.*;

public class CreateProductTest extends TestBase{

    @Test
    public void createProductTest() {
        ProductRequest productRequest = new ProductRequest("Falcon 15", 10, "EUR");

        Response response = ProductAPI.createProduct(productRequest);

        Assert.assertEquals(response.path(TITLE), productRequest.getTitle(),"title didn't match");
        Assert.assertNotNull(response.path(ID), "id is null");
        Assert.assertEquals(response.path(PRICE), productRequest.getPrice(), "price didn't match");
        Assert.assertEquals(response.path(CURRENCY), productRequest.getCurrency(), "currency didn't match");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 201);
    }
}



















