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
import static constants.KeyParameters.POST_ID;

public class GetCommentTest extends TestBase {
    private Integer commentId;
    private Integer postId;
    private Integer relatedProductId;

    @BeforeTest
    public void prepareData() {
        relatedProductId = CreateProduct.createProduct("Rocket", 20, "EUR").path(ID);
        postId = CreatePost.createPost("This is a new post", relatedProductId, "Milica").path(ID);
        commentId = CreateComment.createComment("This is awful", postId).path(ID);
    }

    @Test
    public void getComment() {
        Response response = RestAssuredMethods.get(ApiEndpoints.comment(commentId));
        Assert.assertEquals(response.path(ID), commentId, "id didn't match");
        Assert.assertEquals(response.path(POST_ID), postId, "post id didn't match");

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }
}
