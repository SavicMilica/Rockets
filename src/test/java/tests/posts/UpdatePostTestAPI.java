package tests.posts;
import common.TestBase;
import constants.KeyParameters;
import io.restassured.response.Response;
import models.products.ProductRequest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import apimethods.PostAPI;
import models.posts.PostRequest;
import apimethods.ProductAPI;
import static constants.KeyParameters.*;

public class UpdatePostTestAPI extends TestBase {
    private Integer postId;
    private Integer relatedProductId;

   @BeforeTest
   public void prepareData() {
       relatedProductId = ProductAPI.createProduct(new ProductRequest("Rocket", 100, "DIN")).path(KeyParameters.ID);
       postId = PostAPI.createPost(new PostRequest("New post", relatedProductId, "Milica")).path(KeyParameters.ID);
   }

    @Test
    public void updatePost() {
        PostRequest postRequest = new PostRequest(postId, "Title update", relatedProductId, "Milos");
        Response response = PostAPI.updatePost(postRequest, postId);

        Assert.assertEquals(response.path(ID), postRequest.getId(), "id didn't match");
        Assert.assertEquals(response.path(TITLE), postRequest.getTitle(), "title didn't match");
        Assert.assertEquals(response.path(RELATED_PRODUCT_ID), postRequest.getRelatedProductId(), "product id didn't match");
        Assert.assertEquals(response.path(AUTHOR), postRequest.getAuthor(), "author didn't match");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }

}
