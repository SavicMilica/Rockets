package tests.posts;

import common.RestAssuredMethods;
import common.TestBase;
import constants.ApiEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import models.posts.PostRequest;
import apimethods.products.CreateProduct;
import static constants.KeyParameters.*;

public class CreatePostTest extends TestBase {
    public String POST_TITLE = "New masterpiece";
    public Integer relatedProductId;
    public String POST_AUTHOR = "Milica Savic";

    @BeforeClass
    public void prepareData() {
        relatedProductId = CreateProduct.createProduct("Falcon", 5, "EUR").path(ID);
    }

    @Test
    public void createPost() {
        PostRequest postRequest = new PostRequest();
        postRequest.setTitle(POST_TITLE);
        postRequest.setRelatedProductId(relatedProductId);
        postRequest.setAuthor(POST_AUTHOR);

        Response response = RestAssuredMethods.post(postRequest, ApiEndpoints.POSTS);

        Assert.assertEquals(response.path(TITLE), postRequest.getTitle(), "title didn't match");
        Assert.assertEquals(response.path(RELATED_PRODUCT_ID), postRequest.getRelatedProductId(), "product id didn't match");
        Assert.assertEquals(response.path(AUTHOR), postRequest.getAuthor(), "author didn't match");
        Assert.assertNotNull(response.path(ID), "id is null");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 201);
    }

}
