package tests.posts;

import common.TestBase;
import constants.KeyParameters;
import data.providers.PostData;
import data.providers.ProductData;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import apimethods.PostAPI;
import apimethods.ProductAPI;

public class DeletePostTestAPI extends TestBase {
    Integer postId;
    Integer relatedProductId;

    @BeforeTest
    public void prepareData() {
        relatedProductId = ProductAPI.createProduct(ProductData.prepareProductRequest()).path(KeyParameters.ID);
        postId = PostAPI.createPost(PostData.preparePostData(relatedProductId)).path(KeyParameters.ID);
    }

    @Test
    public void deletePost() {
        Response response = PostAPI.deletePost(postId);

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
