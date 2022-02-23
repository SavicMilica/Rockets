package tests.posts;

import apimethods.PostAPI;
import asserts.PostAssert;
import common.TestBase;
import data.models.posts.Post;
import data.providers.PostData;
import data.providers.ProductData;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import data.models.posts.PostRequest;
import apimethods.ProductAPI;

public class CreatePostTestAPI extends TestBase {
    public Integer relatedProductId;

    @BeforeClass
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
}
