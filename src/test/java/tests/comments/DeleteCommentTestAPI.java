package tests.comments;
import apimethods.CommentAPI;
import common.TestBase;
import constants.KeyParameters;
import data.providers.CommentData;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DeleteCommentTestAPI extends TestBase {

    private Integer postId;
    private Integer commentId;

    @BeforeTest
    public void prepareData() {
        postId = CommentData.getPostId();
        commentId = CommentAPI.createComment(CommentData.prepareCommentData(postId)).path(KeyParameters.ID);
    }

    @Test
    public void deleteComment() {
        Response response = CommentAPI.deleteComment(commentId);

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
