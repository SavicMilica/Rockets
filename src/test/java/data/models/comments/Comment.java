package data.models.comments;

public class Comment {
    private Integer id;
    private String body;
    private Integer postId;

    public Comment() {
    }

    public Comment(Integer id, String body, Integer postId) {
        this.id = id;
        this.body = body;
        this.postId = postId;
    }

    public static Comment parseFullCommentResponse(CommentRequest commentRequest) {
        Comment comment = new Comment();
        comment.setId(commentRequest.getId());
        comment.setBody(commentRequest.getBody());
        comment.setPostId(commentRequest.getPostId());
        return comment;
    }

    public static Comment parseCreatedCommentResponse(Comment createdComment) {
        Comment comment = new Comment();
        comment.setId(createdComment.getId());
        comment.setBody(createdComment.getBody());
        comment.setPostId(createdComment.getPostId());
        return comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }
}
