package products.apimethods;
import io.restassured.response.Response;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static products.constants.Header.CONTENT;
import static products.constants.Header.JSON_FORMAT;
import static products.constants.ProductsPath.PRODUCTS;

public class CreateProduct {
    public static Response createProduct(String title, Integer price, String currency) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("title", title);
        body.put("price", price);
        body.put("currency", currency);

        Response response = given().header(CONTENT, JSON_FORMAT).body(body).post(PRODUCTS);
        return response;
    }
}
