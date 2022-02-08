package products.tests;

import common.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import products.apimethods.CreateProduct;

import static products.constants.Header.CONTENT;
import static products.constants.Header.JSON_FORMAT;
import static products.constants.ProductsPath.PRODUCTS;

public class DeleteProductTest extends TestBase {

    private Integer productId;

    @BeforeTest
    public void prepareData() {
        productId = CreateProduct.createProduct("aaa", 0, "sss").path("id");
    }

    @Test
    public void deleteProduct() {



        RequestSpecification request = RestAssured.given();

        request.header(CONTENT, JSON_FORMAT);

        Response response = request.delete(PRODUCTS + productId);

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
