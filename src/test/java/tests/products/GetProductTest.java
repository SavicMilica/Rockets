package tests.products;
import common.RestAssuredMethods;
import common.TestBase;
import constants.ApiEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import apimethods.products.CreateProduct;
import static constants.KeyParameters.ID;

public class GetProductTest extends TestBase {

    private Integer productId;

    @BeforeClass
    public void prepareData() {
        productId = CreateProduct.createProduct("Rocket", 10, "EUR").path(ID);
    }

    @Test
    public void getProduct() {
        Response response = RestAssuredMethods.get(ApiEndpoints.product(productId));
        Assert.assertEquals(response.path(ID), productId, "id didn't match");
        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
