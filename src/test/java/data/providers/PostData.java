package data.providers;

import data.models.posts.PostRequest;

public class PostData {

    public static PostRequest preparePostData(Integer relatedProductId) {
        return new PostRequest("New masterpiece", relatedProductId, "Milos");
    }

    public static PostRequest preparePostDataForUpdate(Integer postId, Integer relatedProductId) {
        return new PostRequest(postId, "This is awesome", relatedProductId, "Milica");
    }
}
