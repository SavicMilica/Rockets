package tests.posts;
import asserts.PostAssert;
import common.TestBase;
import data.models.posts.Post;
import data.providers.PostData;
import data.providers.ProductData;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import apimethods.PostAPI;
import data.models.posts.PostRequest;
import apimethods.ProductAPI;

public class UpdatePostTestAPI extends TestBase {
    private Post createdPost;
    private Integer relatedProductId;

   @BeforeTest
   public void prepareData() {
       relatedProductId = ProductAPI.createProduct(ProductData.prepareProductRequest()).getId();
       createdPost = PostAPI.createPost(PostData.preparePostData(relatedProductId));
   }

    @Test
    public void updatePost() {
        PostRequest postRequest = PostData.preparePostDataForUpdate(createdPost.getId(), relatedProductId);
        Post actualPost = PostAPI.updatePost(postRequest, createdPost.getId());
        Post expectedPost = Post.parseFullProductResponse(postRequest);

        PostAssert.updatePostAssert(actualPost, expectedPost);
    }

}
