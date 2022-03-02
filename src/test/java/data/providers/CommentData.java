package data.providers;

import apimethods.CommentAPI;
import apimethods.PostAPI;
import apimethods.ProductAPI;
import data.models.comments.CommentRequest;

public class CommentData {

    public static Integer getPostId() {
        Integer relatedProductId = ProductAPI.createProduct(ProductData.prepareProductRequest()).getId();
        Integer postId = PostAPI.createPost(PostData.preparePostData(relatedProductId)).getId();
        return postId;
    }

    public static Integer getCommentId() {
        Integer commentId = CommentAPI.createComment(CommentData.prepareCommentData(getPostId())).getId();
        return commentId;
    }

    public static CommentRequest prepareCommentData(Integer postId) {
        return new CommentRequest("New comment", postId);
    }

    public static CommentRequest prepareCommentDataForUpdate(Integer commentId, Integer postId) {
        return new CommentRequest(commentId,"This is updated comment", postId);
    }
}
