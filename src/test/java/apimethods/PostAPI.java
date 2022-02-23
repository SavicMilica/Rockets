package apimethods;

import common.RestAssuredMethods;
import constants.ApiEndpoints;
import data.models.posts.Post;
import gson.GsonSetup;
import io.restassured.response.Response;
import data.models.posts.PostRequest;

public class PostAPI {

    public static Post createPost(PostRequest postRequest) {
        return GsonSetup.convertJsonToClass
                (RestAssuredMethods.post(postRequest, ApiEndpoints.POSTS), Post.class);
    }

    public static Post updatePost(PostRequest postRequest, Integer postId) {
        return GsonSetup.convertJsonToClass
                (RestAssuredMethods.put(postRequest, ApiEndpoints.post(postId)), Post.class);
    }

    public static Post getPostById(Integer postId) {
        return GsonSetup.convertJsonToClass
                (RestAssuredMethods.get(ApiEndpoints.post(postId)), Post.class);
    }

    public static Response getAllPosts() {
        return RestAssuredMethods.get(ApiEndpoints.POSTS);
    }

    public static EmptyClass deletePost(Integer postId) {
        return GsonSetup.convertJsonToClass
                (RestAssuredMethods.delete(ApiEndpoints.post(postId)), EmptyClass.class);
    }

    public static EmptyClass getPostWithError(Integer postId) {
        return GsonSetup.convertErrorResponse(RestAssuredMethods.get(ApiEndpoints.post(postId)), EmptyClass.class);
    }
}
