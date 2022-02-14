package comments.apimethods;

import comments.models.CommentRequest;
import io.restassured.response.Response;
import posts.apimethods.CreatePost;
import posts.models.PostRequest;
import products.apimethods.CreateProduct;

import static constants.ApiEndpoints.COMMENTS;
import static constants.Header.CONTENT;
import static constants.Header.JSON_FORMAT;
import static constants.KeyParameters.ID;
import static io.restassured.RestAssured.given;

public class UpdateComment {

    public static Response updateComment(Integer id, String body, Integer postId) {
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setId(id);
        commentRequest.setBody(body);
        commentRequest.setPostId(postId);

        Response response = given().header(CONTENT, JSON_FORMAT).body(commentRequest).put(COMMENTS + id);
        return response;
    }
}
