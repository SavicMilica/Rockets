package products.tests;

import common.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import static products.constants.Header.CONTENT;
import static products.constants.Header.JSON_FORMAT;
import static products.constants.ProductsPath.PRODUCTS;

public class GetProductTest {

    private Integer PRODUCT_ID = 3;

    @Test
    public void getProduct() {
        TestBase.setupBaseUrl();

        RequestSpecification request = RestAssured.given();

        request.header(CONTENT, JSON_FORMAT);

        Response response = request.get(PRODUCTS + PRODUCT_ID);

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
