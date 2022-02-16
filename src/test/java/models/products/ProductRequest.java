package models.products;

public class ProductRequest {

    private Integer id;
    private String title;
    private Integer price;
    private String currency;

    public ProductRequest() {
    }

    public ProductRequest(String title, Integer price, String currency) {
        this.title = title;
        this.price = price;
        this.currency = currency;
    }

    public ProductRequest(Integer id, String title, Integer price, String currency) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.currency = currency;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}


