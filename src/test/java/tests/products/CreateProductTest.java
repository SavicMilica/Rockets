package tests.products;
import common.RestAssuredMethods;
import common.TestBase;
import constants.ApiEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import models.products.ProductRequest;
import static constants.KeyParameters.*;

public class CreateProductTest extends TestBase{

    private String PRODUCT_TITLE = "Falcon 15";
    private Integer PRODUCT_PRICE = 50;
    private String PRODUCT_CURRENCY = "EUR";

    @Test
    public void createProductTest() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setTitle(PRODUCT_TITLE);
        productRequest.setPrice(PRODUCT_PRICE);
        productRequest.setCurrency(PRODUCT_CURRENCY);

        //ProductRequest useConstructor = new ProductRequest(PRODUCT_TITLE, PRODUCT_PRICE, PRODUCT_CURRENCY);

        Response response = RestAssuredMethods.post(productRequest, ApiEndpoints.PRODUCTS);

        Assert.assertEquals(response.path(TITLE), productRequest.getTitle(),"title didn't match");
        Assert.assertNotNull(response.path(ID), "id is null");
        Assert.assertEquals(response.path(PRICE), productRequest.getPrice(), "price didn't match");
        Assert.assertEquals(response.path(CURRENCY), productRequest.getCurrency(), "currency didn't match");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 201);
    }
}



















