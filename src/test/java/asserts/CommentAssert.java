package asserts;

import data.models.comments.Comment;
import org.testng.asserts.SoftAssert;


public class CommentAssert {

    public static void createCommentAssert(Comment actualComment, Comment expectedComment) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualComment.getBody(), expectedComment.getBody(), "body didn't match");
        softAssert.assertEquals(actualComment.getPostId(), actualComment.getPostId(), "post id didn't match");
        softAssert.assertNotNull(actualComment.getId(), "id is null");
        softAssert.assertAll();
    }

    public static void getCommentAssert(Comment actualComment, Comment expectedComment) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualComment.getId(), expectedComment.getId(), "id didn't match");
        softAssert.assertEquals(actualComment.getBody(), expectedComment.getBody(), "body didn't match");
        softAssert.assertEquals(actualComment.getPostId(), expectedComment.getPostId(), "post id didn't match");
        softAssert.assertAll();
    }

    public static void updateCommentAssert(Comment actualComment, Comment expectedComment) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualComment.getId(), expectedComment.getId(), "id didn't match");
        softAssert.assertEquals(actualComment.getBody(), expectedComment.getBody(), "body didn't match");
        softAssert.assertEquals(actualComment.getPostId(), expectedComment.getPostId(), "post id didn't match");
        softAssert.assertAll();
    }
}
