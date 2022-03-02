package constants;

public class ApiEndpoints {
    public static final String POSTS = "/posts/";
    public static final String PRODUCTS = "/products/";
    public static final String COMMENTS = "/comments/";

    public static String product(Integer productId) {
        return PRODUCTS + productId;
    }

    public static String post(Integer postId) {
        return POSTS + postId;
    }

    public static String comment(Integer commentId) {
        return COMMENTS + commentId;
    }
}
