package apimethods.products;
import common.RestAssuredMethods;
import constants.ApiEndpoints;
import io.restassured.response.Response;
import models.products.ProductRequest;

public class UpdateProduct {

    public static Response updateProduct(Integer productId, String title, Integer price, String currency) {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setId(productId);
        productRequest.setTitle(title);
        productRequest.setPrice(price);
        productRequest.setCurrency(currency);

        Response response = RestAssuredMethods.put(productRequest, ApiEndpoints.product(productId));
        return response;
    }
}
