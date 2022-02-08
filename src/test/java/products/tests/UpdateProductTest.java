package products.tests;
import common.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import static products.constants.Header.CONTENT;
import static products.constants.Header.JSON_FORMAT;
import static products.constants.ProductsPath.PRODUCTS;

public class UpdateProductTest {

    private Integer PRODUCT_ID = 3;
    private String PRODUCT_TITLE = "Rocket";
    private Integer PRODUCT_PRICE = 100;
    private String PRODUCT_CURRENCY = "DIN";

    @Test
    public void updateProduct() {
        TestBase.setupBaseUrl();

        RequestSpecification request = RestAssured.given();

        request.header(CONTENT, JSON_FORMAT);

        HashMap<String, Object> body = new HashMap();

        body.put("id", PRODUCT_ID);
        body.put("title", PRODUCT_TITLE);
        body.put("price", PRODUCT_PRICE);
        body.put("currency", PRODUCT_CURRENCY);

        request.body(body);

        Response response = request.put(PRODUCTS + PRODUCT_ID);

        ResponseBody myResponse = response.getBody();

        Assert.assertEquals(myResponse.path("title"), PRODUCT_TITLE, "title didn't match");
        Assert.assertEquals(myResponse.path("id"), Integer.valueOf(PRODUCT_ID), "id didn't match");
        Assert.assertEquals(myResponse.path("price"), Integer.valueOf(PRODUCT_PRICE), "price didn't match");
        Assert.assertEquals(myResponse.path("currency"), PRODUCT_CURRENCY, "currency didn't match");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
