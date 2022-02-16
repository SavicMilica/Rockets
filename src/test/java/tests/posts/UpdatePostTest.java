package tests.posts;
import common.RestAssuredMethods;
import common.TestBase;
import constants.ApiEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import apimethods.posts.CreatePost;
import models.posts.PostRequest;
import apimethods.products.CreateProduct;
import static constants.KeyParameters.*;

public class UpdatePostTest extends TestBase {
    private Integer postId;
    private String POST_TITLE = "Title update";
    private Integer relatedProductId;
    private String POST_AUTHOR = "Milica Savic";

   @BeforeTest
   public void prepareData() {
       relatedProductId = CreateProduct.createProduct("Rocket", 100, "DIN").path(ID);
       postId = CreatePost.createPost("New title", relatedProductId, "New author").path(ID);
   }

    @Test
    public void updatePost() {
        PostRequest postRequest = new PostRequest();
        postRequest.setId(postId);
        postRequest.setTitle(POST_TITLE);
        postRequest.setRelatedProductId(relatedProductId);
        postRequest.setAuthor(POST_AUTHOR);

        Response response = RestAssuredMethods.put(postRequest, ApiEndpoints.post(postId));

        Assert.assertEquals(response.path(ID), postRequest.getId(), "id didn't match");
        Assert.assertEquals(response.path(TITLE), postRequest.getTitle(), "title didn't match");
        Assert.assertEquals(response.path(RELATED_PRODUCT_ID), postRequest.getRelatedProductId(), "product id didn't match");
        Assert.assertEquals(response.path(AUTHOR), postRequest.getAuthor(), "author didn't match");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }

}
