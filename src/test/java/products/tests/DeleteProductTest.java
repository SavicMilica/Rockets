package products.tests;

import common.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import products.apimethods.CreateProduct;

import static io.restassured.RestAssured.given;
import static products.constants.Header.CONTENT;
import static products.constants.Header.JSON_FORMAT;
import static products.constants.KeyParameters.ID;
import static products.constants.ProductsPath.PRODUCTS;

public class DeleteProductTest extends TestBase {

    private Integer productId;

    @BeforeClass
    public void prepareData() {
        productId = CreateProduct.createProduct("Rocket", 0, "EUR").path(ID);
    }

    @Test
    public void deleteProduct() {
        Response response = given().header(CONTENT, JSON_FORMAT).delete(PRODUCTS + productId);

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
