package tests.products;
import common.TestBase;
import constants.KeyParameters;
import data.providers.ProductData;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import apimethods.ProductAPI;
import static constants.KeyParameters.ID;

public class GetProductTest extends TestBase {

    private Integer productId;

    @BeforeClass
    public void prepareData() {
        productId = ProductAPI.createProduct(ProductData.prepareProductRequest()).path(KeyParameters.ID);
    }

    @Test
    public void getProduct() {
        Response response = ProductAPI.getProductById(productId);
        Assert.assertEquals(response.path(ID), productId, "id didn't match");
        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
