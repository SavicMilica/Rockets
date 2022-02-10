package products.tests;
import common.TestBase;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;
import products.models.ProductRequest;

import static io.restassured.RestAssured.given;
import static products.constants.Header.CONTENT;
import static products.constants.Header.JSON_FORMAT;
import static products.constants.KeyParameters.*;
import static products.constants.ProductsPath.PRODUCTS;


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

        //CreateProductRequest useConstructor = new CreateProductRequest(PRODUCT_TITLE, PRODUCT_PRICE, PRODUCT_CURRENCY);

        Response response = given().header(CONTENT, JSON_FORMAT).body(productRequest).post(PRODUCTS);
        ResponseBody myResponse = response.getBody();

        Assert.assertEquals(myResponse.path(TITLE), productRequest.getTitle(),"title didn't match");
        Assert.assertNotNull(myResponse.path(ID), "id is null");
        Assert.assertEquals(myResponse.path(PRICE), productRequest.getPrice(), "price didn't match");
        Assert.assertEquals(myResponse.path(CURRENCY), productRequest.getCurrency(), "currency didn't match");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 201);
    }
}



















