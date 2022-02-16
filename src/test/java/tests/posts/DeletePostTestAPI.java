package tests.posts;

import common.TestBase;
import constants.KeyParameters;
import io.restassured.response.Response;
import models.posts.PostRequest;
import models.products.ProductRequest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import apimethods.PostAPI;
import apimethods.ProductAPI;

public class DeletePostTestAPI extends TestBase {
    Integer postId;
    Integer relatedProductId;

    @BeforeTest
    public void prepareData() {
        relatedProductId = ProductAPI.createProduct(new ProductRequest("New product", 10, "DIN")).path(KeyParameters.ID);
        postId = PostAPI.createPost(new PostRequest("New post", relatedProductId, "Milica")).path(KeyParameters.ID);
    }

    @Test
    public void deletePost() {
        Response response = PostAPI.deletePost(postId);

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
