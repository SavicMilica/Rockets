package apimethods;
import models.comments.CommentRequest;
import common.RestAssuredMethods;
import constants.ApiEndpoints;
import io.restassured.response.Response;

public class CommentAPI {

    public static Response createComment(CommentRequest commentRequest) {
        return RestAssuredMethods.post(commentRequest, ApiEndpoints.COMMENTS);
    }

    public static Response updateComment(CommentRequest commentRequest, Integer commentId) {
        return RestAssuredMethods.put(commentRequest, ApiEndpoints.comment(commentId));
    }

    public static Response getCommentById(Integer commentId) {
        return RestAssuredMethods.get(ApiEndpoints.comment(commentId));
    }

    public static Response getAllComments() {
        return RestAssuredMethods.get(ApiEndpoints.COMMENTS);
    }

    public static Response deleteComment(Integer commentId) {
        return RestAssuredMethods.delete(ApiEndpoints.comment(commentId));
    }
}
