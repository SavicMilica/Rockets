package tests.comments;

import apimethods.CommentAPI;
import constants.KeyParameters;
import models.comments.CommentRequest;
import common.TestBase;
import io.restassured.response.Response;
import models.posts.PostRequest;
import models.products.ProductRequest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import apimethods.PostAPI;
import apimethods.ProductAPI;
import static constants.KeyParameters.*;

public class CreateCommentTestAPI extends TestBase {
    public Integer postId;
    public Integer relatedProductId;

    @BeforeTest
    public void prepareData() {
        relatedProductId = ProductAPI.createProduct(new ProductRequest("New product", 10, "EUR")).path(KeyParameters.ID);
        postId = PostAPI.createPost(new PostRequest("New post", relatedProductId, "Milica")).path(KeyParameters.ID);
    }

    @Test
    public void createComment() {
        CommentRequest commentRequest = new CommentRequest("Lovely comment", postId);
        Response response = CommentAPI.createComment(commentRequest);

        Assert.assertEquals(response.path(BODY), commentRequest.getBody(), "body didn't match");
        Assert.assertEquals(response.path(POST_ID), commentRequest.getPostId(), "post id didn't match");
        Assert.assertNotNull(response.path(ID), "id is null");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 201);
    }
}
