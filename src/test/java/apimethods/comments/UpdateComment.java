package apimethods.comments;
import models.comments.CommentRequest;
import common.RestAssuredMethods;
import constants.ApiEndpoints;
import io.restassured.response.Response;

public class UpdateComment {

    public static Response updateComment(Integer commentId, String body, Integer postId) {
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setId(commentId);
        commentRequest.setBody(body);
        commentRequest.setPostId(postId);

        Response response = RestAssuredMethods.put(commentRequest, ApiEndpoints.comment(commentId));
        return response;
    }
}
