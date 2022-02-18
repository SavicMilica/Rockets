package apimethods;
import common.RestAssuredMethods;
import constants.ApiEndpoints;
import io.restassured.response.Response;
import data.models.products.ProductRequest;

public class ProductAPI {
    public static Response createProduct(ProductRequest productRequest) {
        return RestAssuredMethods.post(productRequest, ApiEndpoints.PRODUCTS);
    }

    public static Response updateProduct(ProductRequest productRequest, Integer productId) {
        return RestAssuredMethods.put(productRequest, ApiEndpoints.product(productId));
    }

    public static Response getProductById(Integer productId) {
        return RestAssuredMethods.get(ApiEndpoints.product(productId));
    }

    public static Response getAllProducts() {
        return RestAssuredMethods.get(ApiEndpoints.PRODUCTS);
    }

    public static Response deleteProduct(Integer productId) {
        return RestAssuredMethods.delete(ApiEndpoints.product(productId));
    }
}
