package tests.comments;

import apimethods.CommentAPI;
import asserts.CommentAssert;
import data.models.comments.Comment;
import data.models.comments.CommentRequest;
import common.TestBase;
import data.providers.CommentData;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateCommentTestAPI extends TestBase {

    Integer postId;

    @BeforeTest
    public void prepareData() {
        postId = CommentData.getPostId();
    }

    @Test
    public void createComment() {
        CommentRequest commentRequest = CommentData.prepareCommentData(postId);
        Comment actualComment = CommentAPI.createComment(commentRequest);
        Comment expectedComment = Comment.parseFullCommentResponse(commentRequest);

        CommentAssert.createCommentAssert(actualComment, expectedComment);
    }
}
