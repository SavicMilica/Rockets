package tests.comments;

import apimethods.CommentAPI;
import apimethods.EmptyClass;
import asserts.CommentAssert;
import data.models.comments.Comment;
import data.models.comments.CommentRequest;
import common.TestBase;
import data.providers.CommentData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CommentCRUDTest extends TestBase {
    private Integer postId;
    private Comment createdComment;

    @Test
    public void createComment() {
        postId = CommentData.getPostId();
        CommentRequest commentRequest = CommentData.prepareCommentData(postId);
        Comment actualComment = CommentAPI.createComment(commentRequest);
        Comment expectedComment = Comment.parseFullCommentResponse(commentRequest);
        CommentAssert.createCommentAssert(actualComment, expectedComment);
    }

    @Test
    public void createCommentIfTheListIsEmpty() {
        CommentRequest commentRequest = CommentData.prepareCommentData(null);
        Comment actualComment = CommentAPI.createCommentIfTheListIsEmpty(commentRequest);
        Comment expectedComment = Comment.parseFullCommentResponse(commentRequest);
        CommentAssert.createCommentAssert(actualComment, expectedComment);
    }

    @Test
    public void getComment() {
        postId = CommentData.getPostId();
        createdComment = CommentAPI.createComment(CommentData.prepareCommentData(postId));
        Comment actualComment = CommentAPI.getCommentById(createdComment.getId());
        Comment expectedComment = Comment.parseCreatedCommentResponse(createdComment);
        CommentAssert.getCommentAssert(actualComment, expectedComment);
    }

    @Test
    public void getExistingComment() {
        CommentRequest commentRequest = CommentData.prepareCommentData(null);
        Comment actualComment = CommentAPI.getExistingComment(commentRequest);
        Comment expectedComment = Comment.parseFullCommentResponse(commentRequest);
        CommentAssert.getCommentAssert(actualComment, expectedComment);
    }


    @Test
    public void updateComment() {
        CommentRequest commentRequest = CommentData.prepareCommentDataForUpdate(CommentData.getCommentId(), CommentData.getPostId());
        Comment actualComment = CommentAPI.updateComment(commentRequest, commentRequest.getId());
        Comment expectedComment = Comment.parseFullCommentResponse(commentRequest);
        CommentAssert.updateCommentAssert(actualComment, expectedComment);
    }

    @Test
    public void deleteComment() {
        createdComment = CommentAPI.createComment(CommentData.prepareCommentData(postId));
        CommentAPI.deleteComment(createdComment.getId());
        EmptyClass emptyClass = CommentAPI.getCommentWithError(createdComment.getId());
        Assert.assertNotNull(emptyClass);
    }

    @Test
    public void deleteAllComments() {
        CommentAPI.deleteAllComments();
        List<Comment> commentList = CommentAPI.getAllComments();
    }
}
