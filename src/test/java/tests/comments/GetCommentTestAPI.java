package tests.comments;
import apimethods.CommentAPI;
import common.TestBase;
import constants.KeyParameters;
import io.restassured.response.Response;
import models.comments.CommentRequest;
import models.posts.PostRequest;
import models.products.ProductRequest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import apimethods.PostAPI;
import apimethods.ProductAPI;
import static constants.KeyParameters.ID;
import static constants.KeyParameters.POST_ID;

public class GetCommentTestAPI extends TestBase {
    private Integer commentId;
    private Integer postId;
    private Integer relatedProductId;

    @BeforeTest
    public void prepareData() {
        relatedProductId = ProductAPI.createProduct(new ProductRequest("Falcon", 10, "EUR")).path(KeyParameters.ID);
        postId = PostAPI.createPost(new PostRequest("New post", relatedProductId, "Milica")).path(KeyParameters.ID);
        commentId = CommentAPI.createComment(new CommentRequest("This is awful", postId)).path(KeyParameters.ID);
    }

    @Test
    public void getComment() {
        Response response = CommentAPI.getCommentById(commentId);
        Assert.assertEquals(response.path(ID), commentId, "id didn't match");
        Assert.assertEquals(response.path(POST_ID), postId, "post id didn't match");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
