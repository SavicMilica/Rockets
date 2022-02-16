package tests.posts;

import common.RestAssuredMethods;
import common.TestBase;
import constants.ApiEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import apimethods.posts.CreatePost;
import apimethods.products.CreateProduct;
import static constants.KeyParameters.RELATED_PRODUCT_ID;
import static constants.KeyParameters.ID;

public class GetPostTest extends TestBase {

    Integer relatedProductId;
    Integer postId;

    @BeforeTest
    public void prepareData() {
        relatedProductId = CreateProduct.createProduct("Falcon 100", 200, "EUR").path(ID);
        postId = CreatePost.createPost("Wonderful post", relatedProductId, "Jovana").path(ID);
    }

    @Test
    public void getPost() {
        Response response = RestAssuredMethods.get(ApiEndpoints.post(postId));
        Assert.assertEquals(response.path(ID), postId, "id didn't match");
        Assert.assertEquals(response.path(RELATED_PRODUCT_ID), relatedProductId, "related product id didn't match");
        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
