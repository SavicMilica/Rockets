package apimethods;

import common.RestAssuredMethods;
import constants.ApiEndpoints;
import io.restassured.response.Response;
import models.posts.PostRequest;

public class PostAPI {

    public static Response createPost(PostRequest postRequest) {
        return RestAssuredMethods.post(postRequest, ApiEndpoints.POSTS);
    }

    public static Response updatePost(PostRequest postRequest, Integer postId) {
        return RestAssuredMethods.put(postRequest, ApiEndpoints.post(postId));
    }

    public static Response getPostById(Integer postId) {
        return RestAssuredMethods.get(ApiEndpoints.post(postId));
    }

    public static Response getAllPosts() {
        return RestAssuredMethods.get(ApiEndpoints.POSTS);
    }

    public static Response deletePost(Integer postId) {
        return RestAssuredMethods.delete(ApiEndpoints.post(postId));
    }
}
