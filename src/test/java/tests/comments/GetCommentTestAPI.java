package tests.comments;
import apimethods.CommentAPI;
import common.TestBase;
import constants.KeyParameters;
import data.providers.CommentData;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static constants.KeyParameters.ID;
import static constants.KeyParameters.POST_ID;

public class GetCommentTestAPI extends TestBase {
    private Integer commentId;
    private Integer postId;

    @BeforeTest
    public void prepareData() {
        postId = CommentData.getPostId();
        commentId = CommentAPI.createComment(CommentData.prepareCommentData(postId)).path(KeyParameters.ID);
    }

    @Test
    public void getComment() {
        Response response = CommentAPI.getCommentById(commentId);
        Assert.assertEquals(response.path(ID), commentId, "id didn't match");
        Assert.assertEquals(response.path(POST_ID), postId, "post id didn't match");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
