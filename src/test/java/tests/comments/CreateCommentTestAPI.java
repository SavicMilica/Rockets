package tests.comments;

import apimethods.CommentAPI;
import data.models.comments.CommentRequest;
import common.TestBase;
import data.providers.CommentData;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static constants.KeyParameters.*;

public class CreateCommentTestAPI extends TestBase {

    Integer postId;

    @BeforeTest
    public void prepareData() {
        postId = CommentData.getPostId();
    }

    @Test
    public void createComment() {
        CommentRequest commentRequest = CommentData.prepareCommentData(postId);
        Response response = CommentAPI.createComment(commentRequest);

        Assert.assertEquals(response.path(BODY), commentRequest.getBody(), "body didn't match");
        Assert.assertEquals(response.path(POST_ID), commentRequest.getPostId(), "post id didn't match");
        Assert.assertNotNull(response.path(ID), "id is null");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 201);
    }
}
