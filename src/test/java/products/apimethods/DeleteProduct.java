package products.apimethods;

import common.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static products.constants.Header.CONTENT;
import static products.constants.Header.JSON_FORMAT;
import static products.constants.ProductsPath.PRODUCTS;

public class DeleteProduct {

    private Integer PRODUCT_ID = 5;

    @Test
    public void deleteProduct() {
        TestBase.setupBaseUrl();

        RequestSpecification request = RestAssured.given();

        request.header(CONTENT, JSON_FORMAT);

        Response response = request.delete(PRODUCTS + PRODUCT_ID);
    }
}
