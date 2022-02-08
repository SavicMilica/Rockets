package products.apimethods;

import common.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;

import static products.constants.Header.CONTENT;
import static products.constants.Header.JSON_FORMAT;
import static products.constants.ProductsPath.PRODUCTS;

@Test
public class UpdateProduct {

    private Integer PRODUCT_ID = 3;
    private String PRODUCT_TITLE = "Rocket";
    private Integer PRODUCT_PRICE = 100;
    private String PRODUCT_CURRENCY = "DIN";

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
    }
}
