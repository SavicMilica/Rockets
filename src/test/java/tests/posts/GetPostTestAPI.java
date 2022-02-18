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
import static constants.KeyParameters.RELATED_PRODUCT_ID;
import static constants.KeyParameters.ID;

public class GetPostTestAPI extends TestBase {
    Integer relatedProductId;
    Integer postId;

    @BeforeTest
    public void prepareData() {
        relatedProductId = ProductAPI.createProduct(ProductData.prepareProductRequest()).path(KeyParameters.ID);
        postId = PostAPI.createPost(PostData.preparePostData(relatedProductId)).path(KeyParameters.ID);
    }

    @Test
    public void getPost() {
        Response response = PostAPI.getPostById(postId);
        Assert.assertEquals(response.path(ID), postId, "id didn't match");
        Assert.assertEquals(response.path(RELATED_PRODUCT_ID), relatedProductId, "related product id didn't match");
        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
