package posts.tests;

import common.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import posts.apimethods.CreatePost;
import products.apimethods.CreateProduct;

import static io.restassured.RestAssured.given;
import static constants.ApiEndpoints.POSTS;
import static constants.Header.CONTENT;
import static constants.Header.JSON_FORMAT;
import static constants.KeyParameters.ID;

public class DeletePostTest extends TestBase {

    Integer postId;
    Integer relatedProductId;

    @BeforeTest
    public void prepareData() {
        relatedProductId = CreateProduct.createProduct("New product", 10, "DIN").path(ID);
        postId = CreatePost.createPost("New post", relatedProductId, "Milos").path(ID);
    }

    @Test
    public void deletePost() {
        Response response = given().header(CONTENT, JSON_FORMAT).delete(POSTS + postId);

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
