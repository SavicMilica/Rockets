package tests.comments;
import apimethods.comments.CreateComment;
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

public class UpdateCommentTest extends TestBase {
    private Integer relatedProductId;
    private Integer postId;
    private Integer commentId;
    private String COMMENT_BODY = "This is awesome";

    @BeforeTest
    public void prepareData() {
        relatedProductId = CreateProduct.createProduct("New product", 10, "EUR").path(ID);
        postId = CreatePost.createPost("New post", relatedProductId, "New author").path(ID);
        commentId = CreateComment.createComment("Comment for update", postId).path(ID);
    }

    @Test
    public void updateComment() {
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setId(commentId);
        commentRequest.setBody(COMMENT_BODY);
        commentRequest.setPostId(postId);

        Response response = RestAssuredMethods.put(commentRequest, ApiEndpoints.comment(commentId));

        Assert.assertEquals(response.path(ID), commentRequest.getId(), "id didn't match");
        Assert.assertEquals(response.path(BODY), commentRequest.getBody(), "body didn't match");
        Assert.assertEquals(response.path(POST_ID), commentRequest.getPostId(), "post id didn't match");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
