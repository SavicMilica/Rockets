package tests.products;
import common.RestAssuredMethods;
import common.TestBase;
import constants.ApiEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import apimethods.products.CreateProduct;
import models.products.ProductRequest;
import static constants.KeyParameters.*;

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

        Response response = RestAssuredMethods.put(productRequest, ApiEndpoints.product(productId));

        Assert.assertEquals(response.path(TITLE), productRequest.getTitle(), "title didn't match");
        Assert.assertEquals(response.path(ID), productRequest.getId(), "id didn't match");
        Assert.assertEquals(response.path(PRICE), productRequest.getPrice(), "price didn't match");
        Assert.assertEquals(response.path(CURRENCY), productRequest.getCurrency(), "currency didn't match");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
