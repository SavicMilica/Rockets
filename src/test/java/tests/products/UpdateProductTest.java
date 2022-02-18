package tests.products;
import common.TestBase;
import constants.KeyParameters;
import data.providers.ProductData;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import apimethods.ProductAPI;
import data.models.products.ProductRequest;
import static constants.KeyParameters.*;

public class UpdateProductTest extends TestBase {
    private Integer productId;

    @BeforeClass
    public void prepareData() {
        productId = ProductAPI.createProduct(ProductData.prepareProductRequest()).path(KeyParameters.ID);
    }

    @Test
    public void updateProduct() {
        ProductRequest productRequest = ProductData.prepareProductRequestForUpdate(productId);

        Response response = ProductAPI.updateProduct(productRequest, productId);

        Assert.assertEquals(response.path(TITLE), productRequest.getTitle(), "title didn't match");
        Assert.assertEquals(response.path(ID), productRequest.getId(), "id didn't match");
        Assert.assertEquals(response.path(PRICE), productRequest.getPrice(), "price didn't match");
        Assert.assertEquals(response.path(CURRENCY), productRequest.getCurrency(), "currency didn't match");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
