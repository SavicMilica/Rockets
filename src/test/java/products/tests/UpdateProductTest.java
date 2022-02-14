package products.tests;
import common.TestBase;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import products.apimethods.CreateProduct;
import products.models.ProductRequest;

import static io.restassured.RestAssured.given;
import static constants.Header.CONTENT;
import static constants.Header.JSON_FORMAT;
import static constants.KeyParameters.*;
import static constants.ApiEndpoints.PRODUCTS;

public class UpdateProductTest extends TestBase {

    private Integer productId;
    private String PRODUCT_TITLE = "Rocket";
    private Integer PRODUCT_PRICE = 100;
    private String PRODUCT_CURRENCY = "DIN";

    @BeforeClass
    public void prepareData() {
        productId = CreateProduct.createProduct("Falcon", 5, "EUR").path(ID);
    }

    @Test
    public void updateProduct() {

        ProductRequest productRequest = new ProductRequest();
        productRequest.setId(productId);
        productRequest.setTitle(PRODUCT_TITLE);
        productRequest.setPrice(PRODUCT_PRICE);
        productRequest.setCurrency(PRODUCT_CURRENCY);

        Response response = given().header(CONTENT, JSON_FORMAT).body(productRequest).put(PRODUCTS + productId);
        ResponseBody myResponse = response.getBody();

        Assert.assertEquals(myResponse.path(TITLE), productRequest.getTitle(), "title didn't match");
        Assert.assertEquals(myResponse.path(ID), productRequest.getId(), "id didn't match");
        Assert.assertEquals(myResponse.path(PRICE), productRequest.getPrice(), "price didn't match");
        Assert.assertEquals(myResponse.path(CURRENCY), productRequest.getCurrency(), "currency didn't match");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
