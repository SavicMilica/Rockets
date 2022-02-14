package products.apimethods;

import io.restassured.response.Response;
import products.models.ProductRequest;

import static io.restassured.RestAssured.given;
import static constants.Header.CONTENT;
import static constants.Header.JSON_FORMAT;
import static constants.ApiEndpoints.PRODUCTS;

public class UpdateProduct {

    public static Response updateProduct(Integer id, String title, Integer price, String currency) {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setId(id);
        productRequest.setTitle(title);
        productRequest.setPrice(price);
        productRequest.setCurrency(currency);


        Response response = given().header(CONTENT, JSON_FORMAT).body(productRequest).put(PRODUCTS + id);
        return response;
    }
}
