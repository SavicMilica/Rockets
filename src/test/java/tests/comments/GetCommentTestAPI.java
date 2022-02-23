package tests.comments;
import apimethods.CommentAPI;
import asserts.CommentAssert;
import common.TestBase;
import data.models.comments.Comment;
import data.providers.CommentData;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GetCommentTestAPI extends TestBase {
    private Comment createdComment;
    private Integer postId;

    @BeforeTest
    public void prepareData() {
        createdComment = CommentAPI.createComment(CommentData.prepareCommentData(postId));
    }

    @Test
    public void getComment() {
        Comment actualComment = CommentAPI.getCommentById(createdComment.getId());
        Comment expectedComment = Comment.parseCreatedCommentResponse(createdComment);

        CommentAssert.getCommentAssert(actualComment, expectedComment);
    }
}
