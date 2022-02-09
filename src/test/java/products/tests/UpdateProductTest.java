package products.tests;
import common.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import products.apimethods.CreateProduct;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static products.constants.Header.CONTENT;
import static products.constants.Header.JSON_FORMAT;
import static products.constants.ProductsPath.PRODUCTS;

public class UpdateProductTest extends TestBase {

    private Integer productId;
    private String PRODUCT_TITLE = "Rocket";
    private Integer PRODUCT_PRICE = 100;
    private String PRODUCT_CURRENCY = "DIN";

    @BeforeClass
    public void prepareData() {
        productId = CreateProduct.createProduct("Falcon", 5, "EUR").path("id");
    }

    @Test
    public void updateProduct() {
        HashMap<String, Object> body = new HashMap();
        body.put("id", productId);
        body.put("title", PRODUCT_TITLE);
        body.put("price", PRODUCT_PRICE);
        body.put("currency", PRODUCT_CURRENCY);

        Response response = given().header(CONTENT, JSON_FORMAT).body(body).put(PRODUCTS + productId);
        ResponseBody myResponse = response.getBody();

        Assert.assertEquals(myResponse.path("title"), PRODUCT_TITLE, "title didn't match");
        Assert.assertEquals(myResponse.path("id"), Integer.valueOf(productId), "id didn't match");
        Assert.assertEquals(myResponse.path("price"), Integer.valueOf(PRODUCT_PRICE), "price didn't match");
        Assert.assertEquals(myResponse.path("currency"), PRODUCT_CURRENCY, "currency didn't match");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
