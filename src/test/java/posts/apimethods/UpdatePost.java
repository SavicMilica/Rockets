package posts.apimethods;

import io.restassured.response.Response;
import posts.models.PostRequest;

import static io.restassured.RestAssured.given;
import static posts.constants.PostsPath.POSTS;
import static products.constants.Header.CONTENT;
import static products.constants.Header.JSON_FORMAT;

public class UpdatePost {
    public static Response updatePost(Integer id, String title, Integer relatedProductId, String author) {
        PostRequest postRequest = new PostRequest();
        postRequest.setId(id);
        postRequest.setTitle(title);
        postRequest.setRelatedProductId(relatedProductId);
        postRequest.setAuthor(author);

        Response response = given().header(CONTENT, JSON_FORMAT).body(postRequest).post(POSTS + id);
        return response;
    }
}
