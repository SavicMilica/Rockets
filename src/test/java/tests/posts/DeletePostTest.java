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
import static constants.KeyParameters.ID;

public class DeletePostTest extends TestBase {
    Integer postId;
    Integer relatedProductId;

    @BeforeTest
    public void prepareData() {
        relatedProductId = CreateProduct.createProduct("New product", 10, "DIN").path(ID);
        postId = CreatePost.createPost("New post", relatedProductId, "Milos").path(ID);
    }

    @Test
    public void deletePost() {
        Response response = RestAssuredMethods.delete(ApiEndpoints.post(postId));

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
