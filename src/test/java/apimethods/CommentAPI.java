package apimethods;
import data.models.comments.Comment;
import data.models.comments.CommentRequest;
import common.RestAssuredMethods;
import constants.ApiEndpoints;
import data.providers.CommentData;
import gson.GsonSetup;
import io.restassured.response.Response;

import java.util.List;

public class CommentAPI {

    public static Comment createComment(CommentRequest commentRequest) {
        return GsonSetup.convertJsonToClass
                (RestAssuredMethods.post(commentRequest, ApiEndpoints.COMMENTS), Comment.class);
    }

    public static Comment updateComment(CommentRequest commentRequest, Integer commentId) {
        return GsonSetup.convertJsonToClass
                (RestAssuredMethods.put(commentRequest, ApiEndpoints.comment(commentId)), Comment.class);
    }

    public static Comment getCommentById(Integer commentId) {
        return GsonSetup.convertJsonToClass
                (RestAssuredMethods.get(ApiEndpoints.comment(commentId)), Comment.class);
    }

    public static List<Comment> getAllComments() {
        return GsonSetup.parseSuccessResponseAsListToModel
                (RestAssuredMethods.get(ApiEndpoints.COMMENTS), Comment[].class);
    }

    public static EmptyClass deleteComment(Integer commentId) {
        return GsonSetup.convertJsonToClass
                (RestAssuredMethods.delete(ApiEndpoints.comment(commentId)), EmptyClass.class);
    }

    public static EmptyClass getCommentWithError(Integer commentId) {
        return GsonSetup.convertErrorResponse(RestAssuredMethods.get(ApiEndpoints.comment(commentId)), EmptyClass.class);
    }

    public static Comment createCommentIfTheListIsEmpty(CommentRequest commentRequest) {
        List<Comment> commentList = getAllComments();
        if(commentList.isEmpty()) {
            Integer postId = CommentData.getPostId();
            commentRequest.setPostId(postId);
            return createComment(commentRequest);
        } else {
            return commentList.get(0);
        }
    }

    public static void deleteAllComments() {
        List<Comment> commentList = getAllComments();
        for(int i = 0; i < commentList.size(); i++) {
            deleteComment(commentList.get(i).getId());
        }
    }

    public static Comment getExistingComment(CommentRequest commentRequest) {
        List<Comment> commentList = getAllComments();
            if(commentList.isEmpty()) {
                Integer postId = CommentData.getPostId();
                commentRequest.setPostId(postId);
                Integer commentId = createComment(commentRequest).getId();
                commentRequest.setId(commentId);
                return getCommentById(commentId);
            } else {
                Integer postId = commentList.get(commentList.size() - 1).getPostId();
                commentRequest.setPostId(postId);
                Integer commentId = commentList.get(commentList.size() - 1).getId();
                commentRequest.setId(commentId);
                return getCommentById(commentId);
            }
    }
}
