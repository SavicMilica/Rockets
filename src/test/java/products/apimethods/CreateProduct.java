package products.apimethods;
import io.restassured.response.Response;
import products.models.ProductRequest;

import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static products.constants.Header.CONTENT;
import static products.constants.Header.JSON_FORMAT;
import static products.constants.KeyParameters.*;
import static products.constants.ProductsPath.PRODUCTS;

public class CreateProduct {
    public static Response createProduct(String title, Integer price, String currency) {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setTitle(title);
        productRequest.setPrice(price);
        productRequest.setCurrency(currency);

        Response response = given().header(CONTENT, JSON_FORMAT).body(productRequest).post(PRODUCTS);
        return response;
    }
}
