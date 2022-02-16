package tests.comments;

import models.comments.CommentRequest;
import common.RestAssuredMethods;
import common.TestBase;
import constants.ApiEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import apimethods.posts.CreatePost;
import apimethods.products.CreateProduct;
import static constants.KeyParameters.*;

public class CreateCommentTest extends TestBase {
    public Integer postId;
    public Integer relatedProductId;
    public String COMMENT_BODY = "Lovely comment";

    @BeforeTest
    public void prepareData() {
        relatedProductId = CreateProduct.createProduct("New product", 10, "EUR").path(ID);
        postId = CreatePost.createPost("New post", relatedProductId, "New author").path(ID);
    }

    @Test
    public void createComment() {
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setBody(COMMENT_BODY);
        commentRequest.setPostId(postId);

        Response response = RestAssuredMethods.post(commentRequest, ApiEndpoints.COMMENTS);

        Assert.assertEquals(response.path(BODY), commentRequest.getBody(), "body didn't match");
        Assert.assertEquals(response.path(POST_ID), commentRequest.getPostId(), "post id didn't match");
        Assert.assertNotNull(response.path(ID), "id is null");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 201);
    }
}
