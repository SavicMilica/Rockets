package posts.tests;

import common.TestBase;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import posts.models.PostRequest;
import products.apimethods.CreateProduct;

import static io.restassured.RestAssured.given;
import static posts.constants.KeyParameters.*;
import static posts.constants.PostsPath.POSTS;
import static products.constants.Header.CONTENT;
import static products.constants.Header.JSON_FORMAT;
import static products.constants.KeyParameters.ID;
import static products.constants.KeyParameters.TITLE;
import static products.constants.ProductsPath.PRODUCTS;

public class CreatePostTest extends TestBase {

    public String POST_TITLE = "New masterpiece";
    public Integer relatedProductId;
    public String POST_AUTHOR = "Milica Savic";

    @BeforeClass
    public void prepareData() {
        relatedProductId = CreateProduct.createProduct("Falcon", 5, "EUR").path(ID);
    }

    @Test
    public void createPost() {
        PostRequest postRequest = new PostRequest();
        postRequest.setTitle(POST_TITLE);
        postRequest.setRelatedProductId(relatedProductId);
        postRequest.setAuthor(POST_AUTHOR);

        Response response = given().header(CONTENT, JSON_FORMAT).body(postRequest).post(POSTS);
        ResponseBody myResponse = response.getBody();

        Assert.assertEquals(myResponse.path(TITLE), postRequest.getTitle(), "title didn't match");
        Assert.assertEquals(myResponse.path(RELATED_PRODUCT_ID), postRequest.getRelatedProductId(), "product id didn't match");
        Assert.assertEquals(myResponse.path(AUTHOR), postRequest.getAuthor(), "author didn't match");
        Assert.assertNotNull(myResponse.path(ID), "id is null");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 201);
    }

}
