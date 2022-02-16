package apimethods.products;
import common.RestAssuredMethods;
import constants.ApiEndpoints;
import io.restassured.response.Response;
import models.products.ProductRequest;

public class CreateProduct {
    public static Response createProduct(String title, Integer price, String currency) {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setTitle(title);
        productRequest.setPrice(price);
        productRequest.setCurrency(currency);

        return RestAssuredMethods.post(productRequest, ApiEndpoints.PRODUCTS);
    }
}
