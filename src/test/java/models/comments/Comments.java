package models.comments;

import models.comments.Comment;

import java.util.List;

public class Comments {
    private List<Comment> comments;

    public Comments() {
    }

    public Comments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
