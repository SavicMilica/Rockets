package data.providers;

import apimethods.PostAPI;
import apimethods.ProductAPI;
import constants.KeyParameters;
import data.models.comments.CommentRequest;

public class CommentData {

    public static Integer getPostId() {
        Integer relatedProductId = ProductAPI.createProduct(ProductData.prepareProductRequest()).path(KeyParameters.ID);
        Integer postId = PostAPI.createPost(PostData.preparePostData(relatedProductId)).path(KeyParameters.ID);
        return postId;
    }

    public static CommentRequest prepareCommentData(Integer postId) {
        return new CommentRequest("New comment", postId);
    }

    public static CommentRequest prepareCommentDataForUpdate(Integer commentId, Integer postId) {
        return new CommentRequest(commentId,"This is updated comment", postId);
    }
}
