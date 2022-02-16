package tests.posts;

import apimethods.PostAPI;
import common.TestBase;
import constants.KeyParameters;
import io.restassured.response.Response;
import models.products.ProductRequest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import models.posts.PostRequest;
import apimethods.ProductAPI;
import static constants.KeyParameters.*;

public class CreatePostTestAPI extends TestBase {
    public Integer relatedProductId;

    @BeforeClass
    public void prepareData() {
        relatedProductId = ProductAPI.createProduct(new ProductRequest("Falcon", 20, "EUR")).path(KeyParameters.ID);
    }

    @Test
    public void createPost() {
        PostRequest postRequest = new PostRequest("New masterpiece", relatedProductId, "Milica");
        Response response = PostAPI.createPost(postRequest);

        Assert.assertEquals(response.path(TITLE), postRequest.getTitle(), "title didn't match");
        Assert.assertEquals(response.path(RELATED_PRODUCT_ID), postRequest.getRelatedProductId(), "product id didn't match");
        Assert.assertEquals(response.path(AUTHOR), postRequest.getAuthor(), "author didn't match");
        Assert.assertNotNull(response.path(ID), "id is null");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 201);
    }

}
