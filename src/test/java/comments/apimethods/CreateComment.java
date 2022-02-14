package comments.apimethods;

import comments.models.CommentRequest;
import io.restassured.response.Response;

import static constants.ApiEndpoints.COMMENTS;
import static constants.ApiEndpoints.POSTS;
import static constants.Header.CONTENT;
import static constants.Header.JSON_FORMAT;
import static io.restassured.RestAssured.given;

public class CreateComment {

    public static Response createComment(String body, Integer postId) {
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setBody(body);
        commentRequest.setPostId(postId);

        Response response = given().header(CONTENT, JSON_FORMAT).body(commentRequest).post(COMMENTS);
        return response;
    }
}
