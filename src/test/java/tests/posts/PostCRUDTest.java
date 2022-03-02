package tests.posts;

import apimethods.EmptyClass;
import apimethods.PostAPI;
import asserts.PostAssert;
import common.TestBase;
import data.models.posts.Post;
import data.providers.PostData;
import data.providers.ProductData;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import data.models.posts.PostRequest;
import apimethods.ProductAPI;

import java.util.List;

public class PostCRUDTest extends TestBase {
    private Integer relatedProductId;
    private Post createdPost;

    @BeforeTest
    public void prepareData() {
        relatedProductId = ProductAPI.createProduct(ProductData.prepareProductRequest()).getId();
    }

    @Test
    public void createPost() {
        PostRequest postRequest = PostData.preparePostData(relatedProductId);
        Post actualPost = PostAPI.createPost(postRequest);
        Post expectedPost = Post.parseFullProductResponse(postRequest);
        PostAssert.createPostAssert(actualPost, expectedPost);
    }

    @Test
    public void createNewPostIfTheListIsEmpty() {
        PostRequest postRequest = PostData.preparePostData(relatedProductId);
        Post actualPost = PostAPI.createNewPostIfTheListIsEmpty(postRequest);
        Post expectedPost = Post.parseFullProductResponse(postRequest);
        PostAssert.createPostAssert(actualPost, expectedPost);
    }

    @Test
    public void getPost() {
        createdPost = PostAPI.createPost(PostData.preparePostData(relatedProductId));
        Post actualPost = PostAPI.getPostById(createdPost.getId());
        Post expectedPost = Post.parseFullCreatePostResponse(createdPost);
        PostAssert.getPostAssert(actualPost, expectedPost);
    }

    @Test
    public void getPostFromAList() {
        createdPost = PostAPI.createPost(PostData.preparePostData(relatedProductId));
        Post actualPost = PostAPI.getPostFromAListOfPosts(createdPost.getId());
        Post expectedPost = Post.parseFullCreatePostResponse(createdPost);
        PostAssert.getPostAssert(actualPost, expectedPost);
    }

    @Test
    public void updatePost() {
        createdPost = PostAPI.createPost(PostData.preparePostData(relatedProductId));
        PostRequest postRequest = PostData.preparePostDataForUpdate(createdPost.getId(), relatedProductId);
        Post actualPost = PostAPI.updatePost(postRequest, createdPost.getId());
        Post expectedPost = Post.parseFullProductResponse(postRequest);
        PostAssert.updatePostAssert(actualPost, expectedPost);
    }

    @Test
    public void updateExistingProduct() {
        PostRequest postRequest = PostData.preparePostData(relatedProductId);
        PostRequest postRequestForUpdate = PostData.preparePostDataForUpdate(null, relatedProductId);
        Post actualPost = PostAPI.UpdateExistingPost(postRequest, postRequestForUpdate);
        Post expectedPost = Post.parseFullProductResponse(postRequestForUpdate);
        PostAssert.updatePostAssert(actualPost, expectedPost);
    }

    @Test
    public void deletePost() {
        createdPost = PostAPI.createPost(PostData.preparePostData(relatedProductId));
        PostAPI.deletePost(createdPost.getId());
        EmptyClass emptyClass = PostAPI.getPostWithError(createdPost.getId());
        Assert.assertNotNull(emptyClass);
    }


    @Test
    public void deleteAllPosts() {
        PostAPI.deleteAllPosts();
        List<Post> postList = PostAPI.getAllPosts();
    }

    @Test
    public void deleteExistingProduct() {
        PostRequest postRequest = PostData.preparePostData(relatedProductId);
        PostAPI.deleteExistingProduct(postRequest);
        List<Post> postList = PostAPI.getAllPosts();
    }
}
