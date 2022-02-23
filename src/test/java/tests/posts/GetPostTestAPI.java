package tests.posts;

import asserts.PostAssert;
import common.TestBase;
import data.models.posts.Post;
import data.models.posts.PostRequest;
import data.providers.PostData;
import data.providers.ProductData;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import apimethods.PostAPI;
import apimethods.ProductAPI;


public class GetPostTestAPI extends TestBase {
    Integer relatedProductId;
    Post createdPost;

    @BeforeTest
    public void prepareData() {
        relatedProductId = ProductAPI.createProduct(ProductData.prepareProductRequest()).getId();
        createdPost = PostAPI.createPost(PostData.preparePostData(relatedProductId));
    }

    @Test
    public void getPost() {
        Post actualPost = PostAPI.getPostById(createdPost.getId());
        Post expectedPost = Post.parseFullCreatePostResponse(createdPost);
        PostAssert.getPostAssert(actualPost, expectedPost);
    }
}
