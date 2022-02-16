package apimethods.posts;

import common.RestAssuredMethods;
import constants.ApiEndpoints;
import io.restassured.response.Response;
import models.posts.PostRequest;

public class CreatePost {

    public static Response createPost(String title, Integer relatedProductId, String author) {
        PostRequest postRequest = new PostRequest();
        postRequest.setTitle(title);
        postRequest.setRelatedProductId(relatedProductId);
        postRequest.setAuthor(author);

        Response response = RestAssuredMethods.post(postRequest, ApiEndpoints.POSTS);
        return response;
    }
}
