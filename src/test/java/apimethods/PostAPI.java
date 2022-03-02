package apimethods;

import common.RestAssuredMethods;
import constants.ApiEndpoints;
import data.models.posts.Post;
import data.providers.ProductData;
import gson.GsonSetup;
import io.restassured.response.Response;
import data.models.posts.PostRequest;

import java.util.List;

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

    public static List<Post> getAllPosts() {
        return GsonSetup.parseSuccessResponseAsListToModel
                (RestAssuredMethods.get(ApiEndpoints.POSTS), Post[].class);
    }

    public static EmptyClass deletePost(Integer postId) {
        return GsonSetup.convertJsonToClass
                (RestAssuredMethods.delete(ApiEndpoints.post(postId)), EmptyClass.class);
    }

    public static EmptyClass getPostWithError(Integer postId) {
        return GsonSetup.convertErrorResponse(RestAssuredMethods.get(ApiEndpoints.post(postId)), EmptyClass.class);
    }

    public static Post getPostFromAListOfPosts(Integer postId) {
        List<Post> postList = getAllPosts();
        for (int i = 0; i < postList.size(); i++) {
            if (postList.get(i).getId().equals(postId)) {
                return postList.get(i);
            }
        }
        return null;
    }

    public static void deleteAllPosts() {
        List<Post> postList = getAllPosts();
        for (int i = 0; i < postList.size(); i++) {
            deletePost(postList.get(i).getId());
        }
    }

    public static Post createNewPostIfTheListIsEmpty(PostRequest postRequest) {
        List<Post> postList = getAllPosts();
        if (postList.isEmpty()) {
            return createPost(postRequest);
        } else {
            return postList.get(0);
        }
    }

    public static Post UpdateExistingPost(PostRequest postRequest, PostRequest postRequestForUpdate) {
        List<Post> postList = getAllPosts();
        if(!postList.isEmpty()) {
            Integer postId = postList.get(postList.size() - 1).getId();
            postRequestForUpdate.setId(postId);
            return updatePost(postRequestForUpdate, postId);
        } else {
            Integer postId = createPost(postRequest).getId();
            postRequestForUpdate.setId(postId);
            return updatePost(postRequestForUpdate, postId);
        }
    }


    public static void deleteExistingProduct(PostRequest postRequest) {
        List<Post> postList = getAllPosts();
        if(!postList.isEmpty()) {
            Integer postId = postList.get(postList.size() - 1).getId();
            deletePost(postId);
        } else {
            Integer postId = createPost(postRequest).getId();
            deletePost(postId);
        }
    }
}


