package posts.tests;

import common.TestBase;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import posts.apimethods.CreatePost;
import posts.models.PostRequest;
import products.apimethods.CreateProduct;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static posts.constants.KeyParameters.AUTHOR;
import static posts.constants.KeyParameters.RELATED_PRODUCT_ID;
import static posts.constants.PostsPath.POSTS;
import static products.constants.Header.CONTENT;
import static products.constants.Header.JSON_FORMAT;
import static products.constants.KeyParameters.ID;
import static products.constants.KeyParameters.TITLE;

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

        Response response = given().header(CONTENT, JSON_FORMAT).body(postRequest).put(POSTS + postId);
        ResponseBody myResponse = response.getBody();

        Assert.assertEquals(myResponse.path(ID), postRequest.getId(), "id didn't match");
        Assert.assertEquals(myResponse.path(TITLE), postRequest.getTitle(), "title didn't match");
        Assert.assertEquals(myResponse.path(RELATED_PRODUCT_ID), postRequest.getRelatedProductId(), "product id didn't match");
        Assert.assertEquals(myResponse.path(AUTHOR), postRequest.getAuthor(), "author didn't match");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }

}
