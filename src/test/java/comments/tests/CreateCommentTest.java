package comments.tests;

import comments.models.CommentRequest;
import common.TestBase;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import posts.apimethods.CreatePost;
import products.apimethods.CreateProduct;

import static constants.ApiEndpoints.COMMENTS;
import static constants.Header.CONTENT;
import static constants.Header.JSON_FORMAT;
import static constants.KeyParameters.*;
import static io.restassured.RestAssured.given;

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

        Response response = given().header(CONTENT, JSON_FORMAT).body(commentRequest).post(COMMENTS);
        ResponseBody myResponse = response.getBody();

        Assert.assertEquals(myResponse.path(BODY), commentRequest.getBody(), "body didn't match");
        Assert.assertEquals(myResponse.path(POST_ID), commentRequest.getPostId(), "post id didn't match");
        Assert.assertNotNull(myResponse.path(ID), "id is null");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 201);
    }
}
