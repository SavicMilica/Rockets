package products.tests;
import common.TestBase;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import products.apimethods.CreateProduct;

import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static products.constants.Header.CONTENT;
import static products.constants.Header.JSON_FORMAT;
import static products.constants.ProductsPath.PRODUCTS;


public class CreateProductTest extends TestBase{

    private String PRODUCT_TITLE = "Falcon 15";
    private Integer PRODUCT_PRICE = 50;
    private String PRODUCT_CURRENCY = "EUR";

    @Test
    public void createProductTest() {
    RequestSpecification request = given();

    request.header(CONTENT, JSON_FORMAT);

    HashMap<String, Object> body = new HashMap<>();
            body.put("title", PRODUCT_TITLE);
            body.put("price", PRODUCT_PRICE);
            body.put("currency", PRODUCT_CURRENCY);

            request.body(body);

    Response response = request.post(PRODUCTS);

        ResponseBody myResponse = response.getBody();

        Assert.assertEquals(myResponse.path("title"), PRODUCT_TITLE,"title didn't match");
        Assert.assertNotNull(myResponse.path("id"), "id is null");
        Assert.assertEquals(myResponse.path("price"), Integer.valueOf(PRODUCT_PRICE), "price didn't match");
        Assert.assertEquals(myResponse.path("currency"), PRODUCT_CURRENCY, "currency didn't match");

        int code = response.getStatusCode();

        Assert.assertEquals(code, 201);
    }

}



















