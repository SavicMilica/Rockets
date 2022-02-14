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

public class GetPostTest extends TestBase {

    Integer relatedProductId;
    Integer postId;

    @BeforeTest
    public void prepareData() {
        relatedProductId = CreateProduct.createProduct("Falcon 100", 200, "EUR").path(ID);
        postId = CreatePost.createPost("Wonderful post", relatedProductId, "Jovana").path(ID);
    }

    @Test
    public void getPost() {
        Response response = given().header(CONTENT, JSON_FORMAT).get(POSTS + postId);
        Assert.assertEquals(response.path(ID), postId, "id didn't match");
        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
