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

public class DeleteCommentTestAPI extends TestBase {

    private Integer relatedProductId;
    private Integer postId;
    private Integer commentId;

    @BeforeTest
    public void prepareData() {
        relatedProductId = ProductAPI.createProduct(new ProductRequest("New product", 50, "EUR")).path(KeyParameters.ID);
        postId = PostAPI.createPost(new PostRequest("New post", relatedProductId, "Milica")).path(KeyParameters.ID);
        commentId = CommentAPI.createComment(new CommentRequest("Comment for deleting", postId)).path(KeyParameters.ID);
    }

    @Test
    public void deleteComment() {
        Response response = CommentAPI.deleteComment(commentId);

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
