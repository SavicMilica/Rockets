package tests.comments;
import apimethods.CommentAPI;
import constants.KeyParameters;
import models.comments.CommentRequest;
import common.RestAssuredMethods;
import common.TestBase;
import constants.ApiEndpoints;
import io.restassured.response.Response;
import models.posts.PostRequest;
import models.products.ProductRequest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import apimethods.PostAPI;
import apimethods.ProductAPI;
import static constants.KeyParameters.*;

public class UpdateCommentTestAPI extends TestBase {
    private Integer relatedProductId;
    private Integer postId;
    private Integer commentId;
    private String COMMENT_BODY = "This is awesome";

    @BeforeTest
    public void prepareData() {
        relatedProductId = ProductAPI.createProduct(new ProductRequest("Rocket", 10, "EUR")).path(KeyParameters.ID);
        postId = PostAPI.createPost(new PostRequest("New post", relatedProductId, "Milica")).path(KeyParameters.ID);
        commentId = CommentAPI.createComment(new CommentRequest("Comment for update", postId)).path(KeyParameters.ID);
    }

    @Test
    public void updateComment() {
        CommentRequest commentRequest = new CommentRequest(commentId, "This is awesome", postId);

        Response response = CommentAPI.updateComment(commentRequest, commentId);

        Assert.assertEquals(response.path(ID), commentRequest.getId(), "id didn't match");
        Assert.assertEquals(response.path(BODY), commentRequest.getBody(), "body didn't match");
        Assert.assertEquals(response.path(POST_ID), commentRequest.getPostId(), "post id didn't match");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
