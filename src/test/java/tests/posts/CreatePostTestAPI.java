package tests.posts;

import apimethods.PostAPI;
import common.TestBase;
import constants.KeyParameters;
import data.providers.PostData;
import data.providers.ProductData;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import data.models.posts.PostRequest;
import apimethods.ProductAPI;
import static constants.KeyParameters.*;

public class CreatePostTestAPI extends TestBase {
    public Integer relatedProductId;

    @BeforeClass
    public void prepareData() {
        relatedProductId = ProductAPI.createProduct(ProductData.prepareProductRequest()).path(KeyParameters.ID);
    }

    @Test
    public void createPost() {
        PostRequest postRequest = PostData.preparePostData(relatedProductId);
        Response response = PostAPI.createPost(postRequest);

        Assert.assertEquals(response.path(TITLE), postRequest.getTitle(), "title didn't match");
        Assert.assertEquals(response.path(RELATED_PRODUCT_ID), postRequest.getRelatedProductId(), "product id didn't match");
        Assert.assertEquals(response.path(AUTHOR), postRequest.getAuthor(), "author didn't match");
        Assert.assertNotNull(response.path(ID), "id is null");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 201);
    }

}
