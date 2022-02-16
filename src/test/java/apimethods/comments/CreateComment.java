package apimethods.comments;
import models.comments.CommentRequest;
import common.RestAssuredMethods;
import constants.ApiEndpoints;
import io.restassured.response.Response;

public class CreateComment {

    public static Response createComment(String body, Integer postId) {
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setBody(body);
        commentRequest.setPostId(postId);

        Response response = RestAssuredMethods.post(commentRequest, ApiEndpoints.COMMENTS);
        return response;
    }
}
