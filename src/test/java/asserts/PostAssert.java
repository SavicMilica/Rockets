package asserts;

import apimethods.EmptyClass;
import data.models.posts.Post;
import org.testng.asserts.SoftAssert;

public class PostAssert {

    public static void createPostAssert(Post actualPost, Post expectedPost) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualPost.getTitle(), expectedPost.getTitle(), "title didn't match");
        softAssert.assertEquals(actualPost.getRelatedProductId(), expectedPost.getRelatedProductId(), "product id didn't match");
        softAssert.assertEquals(actualPost.getAuthor(), expectedPost.getAuthor(), "author didn't match");
        softAssert.assertNotNull(actualPost.getId(), "id is null");
        softAssert.assertAll();
    }

    public static void getPostAssert(Post actualPost, Post expectedPost) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualPost.getId(), expectedPost.getId(), "id didn't match");
        softAssert.assertEquals(actualPost.getRelatedProductId(), expectedPost.getRelatedProductId(), "related product id didn't match");
        softAssert.assertEquals(actualPost.getTitle(), expectedPost.getTitle(), "title didn't match");
        softAssert.assertEquals(actualPost.getAuthor(), expectedPost.getAuthor(), "author didn't match");
        softAssert.assertAll();
    }

    public static void updatePostAssert(Post actualPost, Post expectedPost) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualPost.getId(), expectedPost.getId(), "id didn't match");
        softAssert.assertEquals(actualPost.getTitle(), expectedPost.getTitle(), "title didn't match");
        softAssert.assertEquals(actualPost.getRelatedProductId(), expectedPost.getRelatedProductId(), "product id didn't match");
        softAssert.assertEquals(actualPost.getAuthor(), expectedPost.getAuthor(), "author didn't match");
        softAssert.assertAll();
    }
}
