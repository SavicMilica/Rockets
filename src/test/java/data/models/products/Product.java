package data.models.products;

public class Product {
    private Integer id;
    private String title;
    private Integer price;
    private String currency;

    public Product() {
    }

    public Product(Integer id, String title, Integer price, String currency) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.currency = currency;
    }

    public static Product parseFullCreateProductResponse(ProductRequest productRequest) {
        Product productResponse = new Product();
        productResponse.setId(productRequest.getId());
        productResponse.setTitle(productRequest.getTitle());
        productResponse.setPrice(productRequest.getPrice());
        productResponse.setCurrency(productRequest.getCurrency());
        return productResponse;
    }

    public static Product parseFullProductResponse(Product createdProduct) {
        Product product = new Product();
        product.setId(createdProduct.getId());
        product.setTitle(createdProduct.getTitle());
        product.setPrice(createdProduct.getPrice());
        product.setCurrency(createdProduct.getCurrency());
        return product;
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


