package tests.comments;
import apimethods.CommentAPI;
import apimethods.EmptyClass;
import common.TestBase;
import data.providers.CommentData;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DeleteCommentTestAPI extends TestBase {

    private Integer commentId;

    @BeforeTest
    public void prepareData() {
        commentId = CommentData.getCommentId();
    }

    @Test
    public void deleteComment() {
        CommentAPI.deleteComment(commentId);
        EmptyClass emptyClass = CommentAPI.getCommentWithError(commentId);
        Assert.assertNotNull(emptyClass);
    }
}
