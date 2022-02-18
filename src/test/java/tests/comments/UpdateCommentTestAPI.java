package tests.comments;
import apimethods.CommentAPI;
import constants.KeyParameters;
import data.models.comments.CommentRequest;
import common.TestBase;
import data.providers.CommentData;
import io.restassured.response.Response;
import data.models.posts.PostRequest;
import data.models.products.ProductRequest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import apimethods.PostAPI;
import apimethods.ProductAPI;
import static constants.KeyParameters.*;

public class UpdateCommentTestAPI extends TestBase {
    private Integer postId;
    private Integer commentId;

    @BeforeTest
    public void prepareData() {
        postId = CommentData.getPostId();
        commentId = CommentAPI.createComment(CommentData.prepareCommentData(postId)).path(KeyParameters.ID);
    }

    @Test
    public void updateComment() {
        CommentRequest commentRequest = CommentData.prepareCommentDataForUpdate(commentId, postId);

        Response response = CommentAPI.updateComment(commentRequest, commentId);

        Assert.assertEquals(response.path(ID), commentRequest.getId(), "id didn't match");
        Assert.assertEquals(response.path(BODY), commentRequest.getBody(), "body didn't match");
        Assert.assertEquals(response.path(POST_ID), commentRequest.getPostId(), "post id didn't match");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
