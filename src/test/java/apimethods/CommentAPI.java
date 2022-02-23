package apimethods;
import data.models.comments.Comment;
import data.models.comments.CommentRequest;
import common.RestAssuredMethods;
import constants.ApiEndpoints;
import data.models.products.Product;
import gson.GsonSetup;
import io.restassured.response.Response;

public class CommentAPI {

    public static Comment createComment(CommentRequest commentRequest) {
        return GsonSetup.convertJsonToClass
                (RestAssuredMethods.post(commentRequest, ApiEndpoints.COMMENTS), Comment.class);
    }

    public static Comment updateComment(CommentRequest commentRequest, Integer commentId) {
        return GsonSetup.convertJsonToClass
                (RestAssuredMethods.put(commentRequest, ApiEndpoints.comment(commentId)), Comment.class);
    }

    public static Comment getCommentById(Integer commentId) {
        return GsonSetup.convertJsonToClass
                (RestAssuredMethods.get(ApiEndpoints.comment(commentId)), Comment.class);
    }

    public static Response getAllComments() {
        return RestAssuredMethods.get(ApiEndpoints.COMMENTS);
    }

    public static EmptyClass deleteComment(Integer commentId) {
        return GsonSetup.convertJsonToClass
                (RestAssuredMethods.delete(ApiEndpoints.comment(commentId)), EmptyClass.class);
    }

    public static EmptyClass getCommentWithError(Integer commentId) {
        return GsonSetup.convertErrorResponse(RestAssuredMethods.get(ApiEndpoints.comment(commentId)), EmptyClass.class);
    }
}
