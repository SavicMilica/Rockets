package models.posts;

public class Post {
    private Integer id;
    private String title;
    private Integer relatedProductId;
    private String author;

    public Post() {
    }

    public Post(Integer id, String title, Integer relatedProductId, String author) {
        this.id = id;
        this.title = title;
        this.relatedProductId = relatedProductId;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRelatedProductId() {
        return relatedProductId;
    }

    public void setRelatedProductId(Integer relatedProductId) {
        this.relatedProductId = relatedProductId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
