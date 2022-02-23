package tests.comments;
import apimethods.CommentAPI;
import asserts.CommentAssert;
import data.models.comments.Comment;
import data.models.comments.CommentRequest;
import common.TestBase;
import data.providers.CommentData;
import org.testng.annotations.Test;

public class UpdateCommentTestAPI extends TestBase {

    @Test
    public void updateComment() {
        CommentRequest commentRequest = CommentData.prepareCommentDataForUpdate(CommentData.getCommentId(), CommentData.getPostId());
        Comment actualComment = CommentAPI.updateComment(commentRequest, commentRequest.getId());
        Comment expectedComment = Comment.parseFullCommentResponse(commentRequest);

        CommentAssert.updateCommentAssert(actualComment, expectedComment);
    }
}
