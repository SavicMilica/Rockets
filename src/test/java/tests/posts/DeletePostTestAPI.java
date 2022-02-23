package tests.posts;

import apimethods.EmptyClass;
import common.TestBase;
import data.providers.PostData;
import data.providers.ProductData;
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
        relatedProductId = ProductAPI.createProduct(ProductData.prepareProductRequest()).getId();
        postId = PostAPI.createPost(PostData.preparePostData(relatedProductId)).getId();
    }

    @Test
    public void deletePost() {

        PostAPI.deletePost(postId);
        EmptyClass emptyClass = PostAPI.getPostWithError(postId);
        Assert.assertNotNull(emptyClass);
    }
}
