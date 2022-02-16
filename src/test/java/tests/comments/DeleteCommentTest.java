package tests.comments;
import apimethods.comments.CreateComment;
import common.RestAssuredMethods;
import common.TestBase;
import constants.ApiEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import apimethods.posts.CreatePost;
import apimethods.products.CreateProduct;
import static constants.KeyParameters.ID;

public class DeleteCommentTest extends TestBase {

    private Integer relatedProductId;
    private Integer postId;
    private Integer commentId;

    @BeforeTest
    public void prepareData() {
        relatedProductId = CreateProduct.createProduct("New product", 10, "EUR").path(ID);
        postId = CreatePost.createPost("New post", relatedProductId, "New author").path(ID);
        commentId = CreateComment.createComment("Comment for deleting", postId).path(ID);
    }

    @Test
    public void deleteComment() {
        Response response = RestAssuredMethods.delete(ApiEndpoints.comment(commentId));

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
