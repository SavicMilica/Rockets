package apimethods.posts;

import common.RestAssuredMethods;
import constants.ApiEndpoints;
import io.restassured.response.Response;
import models.posts.PostRequest;

public class UpdatePost {
    public static Response updatePost(Integer postId, String title, Integer relatedProductId, String author) {
        PostRequest postRequest = new PostRequest();
        postRequest.setId(postId);
        postRequest.setTitle(title);
        postRequest.setRelatedProductId(relatedProductId);
        postRequest.setAuthor(author);

        Response response = RestAssuredMethods.put(postRequest, ApiEndpoints.post(postId));
        return response;
    }
}
