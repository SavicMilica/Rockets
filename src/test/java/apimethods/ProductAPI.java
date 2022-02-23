package apimethods;
import common.RestAssuredMethods;
import constants.ApiEndpoints;
import data.models.products.Product;
import gson.GsonSetup;
import io.restassured.response.Response;
import data.models.products.ProductRequest;

public class ProductAPI {
    public static Product createProduct(ProductRequest productRequest) {
        return GsonSetup.convertJsonToClass
                (RestAssuredMethods.post(productRequest, ApiEndpoints.PRODUCTS), Product.class);
    }

    public static Product updateProduct(ProductRequest productRequest, Integer productId) {
        return GsonSetup.convertJsonToClass
                (RestAssuredMethods.put(productRequest, ApiEndpoints.product(productId)), Product.class);
    }

    public static Product getProductById(Integer productId) {
        return GsonSetup.convertJsonToClass
                (RestAssuredMethods.get(ApiEndpoints.product(productId)), Product.class);
    }

    public static Response getAllProducts() {

        return RestAssuredMethods.get(ApiEndpoints.PRODUCTS);
    }

    public static EmptyClass deleteProduct(Integer productId) {
        return GsonSetup.convertJsonToClass
                (RestAssuredMethods.delete(ApiEndpoints.product(productId)), EmptyClass.class);
    }

    public static EmptyClass getProductWithError(Integer productId) {
        return GsonSetup.convertErrorResponse(RestAssuredMethods.get(ApiEndpoints.product(productId)), EmptyClass.class);
    }
}
