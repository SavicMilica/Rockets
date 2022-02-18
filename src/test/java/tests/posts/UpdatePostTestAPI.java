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
import data.models.posts.PostRequest;
import apimethods.ProductAPI;
import static constants.KeyParameters.*;

public class UpdatePostTestAPI extends TestBase {
    private Integer postId;
    private Integer relatedProductId;

   @BeforeTest
   public void prepareData() {
       relatedProductId = ProductAPI.createProduct(ProductData.prepareProductRequest()).path(KeyParameters.ID);
       postId = PostAPI.createPost(PostData.preparePostData(relatedProductId)).path(KeyParameters.ID);
   }

    @Test
    public void updatePost() {
        PostRequest postRequest = PostData.preparePostDataForUpdate(postId, relatedProductId);
        Response response = PostAPI.updatePost(postRequest, postId);

        Assert.assertEquals(response.path(ID), postRequest.getId(), "id didn't match");
        Assert.assertEquals(response.path(TITLE), postRequest.getTitle(), "title didn't match");
        Assert.assertEquals(response.path(RELATED_PRODUCT_ID), postRequest.getRelatedProductId(), "product id didn't match");
        Assert.assertEquals(response.path(AUTHOR), postRequest.getAuthor(), "author didn't match");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }

}
